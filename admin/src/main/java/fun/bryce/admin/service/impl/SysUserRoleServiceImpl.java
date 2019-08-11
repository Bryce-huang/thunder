package fun.bryce.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.bryce.api.entity.SysUserRole;
import fun.bryce.admin.mapper.SysUserRoleMapper;
import fun.bryce.admin.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService
{

    /**
     * 根据用户Id删除该用户的角色关系
     *
     * @param userId 用户ID
     * @return boolean

     */
    @Override
    public Boolean removeRoleByUserId(Integer userId)
    {
        return baseMapper.deleteByUserId(userId);
    }
}
