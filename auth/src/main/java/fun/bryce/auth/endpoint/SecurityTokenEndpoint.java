

package fun.bryce.auth.endpoint;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.bryce.core.constant.CommonConstants;
import fun.bryce.core.constant.SecurityConstants;
import fun.bryce.core.util.R;
import fun.bryce.security.service.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ConvertingCursor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bryce
 * @date 2019/07/1
 * 删除token端点
 */
@RestController
@AllArgsConstructor
@RequestMapping("/token")
public class SecurityTokenEndpoint
{
    private static final String PROJECT_OAUTH_ACCESS = SecurityConstants.PROJECT_PREFIX + SecurityConstants.OAUTH_PREFIX + "access:";
    private static final String CURRENT = "current";
    private static final String SIZE = "size";
    private final TokenStore tokenStore;
    private final RedisTemplate redisTemplate;

    /**
     * 退出并删除token
     *
     * @param authHeader Authorization
     */
    @DeleteMapping("/logout")
    public R<Boolean> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader)
    {
        if (StrUtil.isBlank(authHeader))
        {
            R<Boolean> r = new R<>();
            r.setMsg("退出失败，token 为空");
            r.setCode(String.valueOf(CommonConstants.SUCCESS));
            r.setData(false);
            return r;
        }

        String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, StrUtil.EMPTY).trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        if (accessToken == null || StrUtil.isBlank(accessToken.getValue()))
        {


            R<Boolean> r = new R<>();
            r.setMsg("退出失败，token 无效");
            r.setCode(String.valueOf(CommonConstants.SUCCESS));
            r.setData(false);
            return r;
        }
        tokenStore.removeAccessToken(accessToken);

        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
        tokenStore.removeRefreshToken(refreshToken);
        R<Boolean> r = new R<>();
        r.setCode(String.valueOf(CommonConstants.SUCCESS));
        r.setData(true);
        return r;
       
    }

    /**
     * 令牌管理调用
     *
     * @param token token
     * @param from  内部调用标志
     */
    @DeleteMapping("/{token}")
    public R<Boolean> removeToken(@PathVariable("token") String token, @RequestHeader(required = false) String from)
    {
        if (StrUtil.isBlank(from))
        {
            return null;
        }
        return R.success(redisTemplate.delete(PROJECT_OAUTH_ACCESS + token));
    }


    /**
     * 查询token
     *
     * @param params 分页参数
     * @param from   标志
     */
    @PostMapping("/page")
    public R getTokenPage(@RequestBody Map<String, Object> params, @RequestHeader(required = false) String from)
    {
        if (StrUtil.isBlank(from))
        {
            return null;
        }

        List<Map<String, String>> list = new ArrayList<>();
        if (StringUtils.isEmpty(MapUtil.getInt(params, CURRENT)) || StringUtils.isEmpty(MapUtil.getInt(params, SIZE)))
        {
            params.put(CURRENT, 1);
            params.put(SIZE, 20);
        }
        //根据分页参数获取对应数据
        List<String> pages = findKeysForPage(PROJECT_OAUTH_ACCESS + "*", MapUtil.getInt(params, CURRENT), MapUtil.getInt(params, SIZE));

        for (String page : pages)
        {
            String accessToken = StrUtil.subAfter(page, PROJECT_OAUTH_ACCESS, true);
            OAuth2AccessToken token = tokenStore.readAccessToken(accessToken);
            Map<String, String> map = new HashMap<>(8);


            map.put(OAuth2AccessToken.TOKEN_TYPE, token.getTokenType());
            map.put(OAuth2AccessToken.ACCESS_TOKEN, token.getValue());
            map.put(OAuth2AccessToken.EXPIRES_IN, token.getExpiresIn() + "");


            OAuth2Authentication oAuth2Auth = tokenStore.readAuthentication(token);
            Authentication authentication = oAuth2Auth.getUserAuthentication();

            map.put(OAuth2Utils.CLIENT_ID, oAuth2Auth.getOAuth2Request().getClientId());
            map.put(OAuth2Utils.GRANT_TYPE, oAuth2Auth.getOAuth2Request().getGrantType());

            if (authentication instanceof UsernamePasswordAuthenticationToken)
            {
                UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

                if (authenticationToken.getPrincipal() instanceof SecurityUser)
                {
                    SecurityUser user = (SecurityUser) authenticationToken.getPrincipal();
                    map.put("user_id", user.getId() + "");
                    map.put("username", user.getUsername() + "");
                }
            } else if (authentication instanceof PreAuthenticatedAuthenticationToken)
            {
                //刷新token方式
                PreAuthenticatedAuthenticationToken authenticationToken = (PreAuthenticatedAuthenticationToken) authentication;
                if (authenticationToken.getPrincipal() instanceof SecurityUser)
                {
                    SecurityUser user = (SecurityUser) authenticationToken.getPrincipal();
                    map.put("user_id", user.getId() + "");
                    map.put("username", user.getUsername() + "");
                }
            }
            list.add(map);
        }

        Page result = new Page(MapUtil.getInt(params, CURRENT), MapUtil.getInt(params, SIZE));
        result.setRecords(list);
        result.setTotal(Long.valueOf(redisTemplate.keys(PROJECT_OAUTH_ACCESS + "*").size()));
        return R.success(result);

    }

    private List<String> findKeysForPage(String patternKey, int pageNum, int pageSize)
    {
        ScanOptions options = ScanOptions.scanOptions().match(patternKey).build();
        RedisSerializer<String> redisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
        Cursor cursor = (Cursor) redisTemplate.executeWithStickyConnection(redisConnection -> new ConvertingCursor<>(redisConnection.scan(options), redisSerializer::deserialize));
        List<String> result = new ArrayList<>();
        int tmpIndex = 0;
        int startIndex = (pageNum - 1) * pageSize;
        int end = pageNum * pageSize;

        assert cursor != null;
        while (cursor.hasNext())
        {
            if (tmpIndex >= startIndex && tmpIndex < end)
            {
                result.add(cursor.next().toString());
                tmpIndex++;
                continue;
            }
            if (tmpIndex >= end)
            {
                break;
            }
            tmpIndex++;
            cursor.next();
        }
        return result;
    }
}
