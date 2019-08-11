package fun.bryce.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.bryce.api.entity.SysOauthClientDetails;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
public interface SysOauthClientDetailsService extends IService<SysOauthClientDetails>
{
    /**
     * 通过ID删除客户端
     *
     * @param id
     * @return
     */
    Boolean removeClientDetailsById(String id);

    /**
     * 根据客户端信息
     *
     * @param sysOauthClientDetails
     * @return
     */
    Boolean updateClientDetailsById(SysOauthClientDetails sysOauthClientDetails);
}
