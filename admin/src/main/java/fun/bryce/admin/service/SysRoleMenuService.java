package fun.bryce.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import fun.bryce.api.entity.SysRoleMenu;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
public interface SysRoleMenuService extends IService<SysRoleMenu>
{

    /**
     * 更新角色菜单
     *
     * @param role
     * @param roleId  角色
     * @param menuIds 菜单ID拼成的字符串，每个id之间根据逗号分隔
     * @return
     */
    Boolean saveRoleMenus(String role, Integer roleId, String menuIds);
}
