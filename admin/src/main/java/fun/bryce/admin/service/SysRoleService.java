package fun.bryce.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.bryce.api.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
public interface SysRoleService extends IService<SysRole>
{

    /**
     * 通过用户ID，查询角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> listRolesByUserId(Integer userId);

    /**
     * 通过角色ID，删除角色
     *
     * @param id
     * @return
     */
    Boolean removeRoleById(Integer id);
}
