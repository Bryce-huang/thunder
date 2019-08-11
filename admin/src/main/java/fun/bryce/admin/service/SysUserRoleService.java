package fun.bryce.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import fun.bryce.api.entity.SysUserRole;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
public interface SysUserRoleService extends IService<SysUserRole>
{

    /**
     * 根据用户Id删除该用户的角色关系
     *
     * @param userId 用户ID
     * @return boolean

     */
    Boolean removeRoleByUserId(Integer userId);
}
