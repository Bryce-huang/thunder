

package fun.bryce.auth;


import fun.bryce.security.annotation.EnableSecurityFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author bryce
 * @date 2019年06月21日
 * 认证授权中心
 */
@SpringCloudApplication
@EnableSecurityFeignClients(basePackages = "fun.bryce")
public class AuthApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(AuthApplication.class, args);
    }
}
