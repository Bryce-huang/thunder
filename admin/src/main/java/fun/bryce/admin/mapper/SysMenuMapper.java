package fun.bryce.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.bryce.api.entity.SysMenu;
import fun.bryce.api.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
public interface SysMenuMapper extends BaseMapper<SysMenu>
{

    /**
     * 通过角色编号查询菜单
     *
     * @param roleId 角色ID
     * @return
     */
    List<MenuVO> listMenusByRoleId(Integer roleId);

    /**
     * 通过角色ID查询权限
     *
     * @param roleIds Ids
     * @return
     */
    List<String> listPermissionsByRoleIds(String roleIds);
}
