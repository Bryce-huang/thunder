package fun.bryce.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.bryce.api.entity.SysRole;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
public interface SysRoleMapper extends BaseMapper<SysRole>
{
    /**
     * 通过用户ID，查询角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> listRolesByUserId(Integer userId);
}
