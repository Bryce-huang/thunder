package fun.bryce.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.bryce.admin.mapper.SysMenuMapper;
import fun.bryce.admin.mapper.SysRoleMenuMapper;
import fun.bryce.admin.service.SysMenuService;
import fun.bryce.api.entity.SysMenu;
import fun.bryce.api.entity.SysRoleMenu;
import fun.bryce.api.vo.MenuVO;
import fun.bryce.core.constant.CommonConstants;
import fun.bryce.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService
{
    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    @Cacheable(value = "menu_details", key = "#roleId  + '_menu'")
    public List<MenuVO> getMenuByRoleId(Integer roleId)
    {
        return baseMapper.listMenusByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menu_details", allEntries = true)
    public R removeMenuById(Integer id)
    {
        // 查询父节点为当前节点的节点
        List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query()
                .lambda().eq(SysMenu::getParentId, id));
        if (CollUtil.isNotEmpty(menuList))
        {
            R r = new R();
            r.setCode(String.valueOf(CommonConstants.FAIL));
            r.setMsg("菜单含有下级不能删除");
            return r;
        }

        sysRoleMenuMapper
                .delete(Wrappers.<SysRoleMenu>query()
                        .lambda().eq(SysRoleMenu::getMenuId, id));

        //删除当前菜单及其子菜单
        return R.success(this.removeById(id));
    }

    @Override
    @CacheEvict(value = "menu_details", allEntries = true)
    public Boolean updateMenuById(SysMenu sysMenu)
    {
        return this.updateById(sysMenu);
    }
}
