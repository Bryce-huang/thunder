package fun.bryce.admin.controller;

import fun.bryce.api.feign.RemoteTokenService;
import fun.bryce.core.constant.SecurityConstants;
import fun.bryce.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author bryce
 * @date 2018/9/4
 * getTokenPage 管理
 */
@RestController
@AllArgsConstructor
@RequestMapping("/token")
public class TokenController
{
    private final RemoteTokenService remoteTokenService;

    /**
     * 分页token 信息
     *
     * @param params 参数集
     * @return token集合
     */
    @GetMapping("/page")
    public R token(@RequestParam Map<String, Object> params)
    {
        return remoteTokenService.getTokenPage(params, SecurityConstants.FROM_IN);
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_token_del')")
    public R<Boolean> delete(@PathVariable String id)
    {
        return remoteTokenService.removeToken(id, SecurityConstants.FROM_IN);
    }
}
