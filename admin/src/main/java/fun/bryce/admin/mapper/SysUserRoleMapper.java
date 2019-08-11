package fun.bryce.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.bryce.api.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole>
{
    /**
     * 根据用户Id删除该用户的角色关系
     *
     * @param userId 用户ID
     * @return boolean
     */
    Boolean deleteByUserId(@Param("userId") Integer userId);
}
