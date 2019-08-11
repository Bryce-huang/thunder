package fun.bryce.admin;


import fun.bryce.security.annotation.EnableSecurityResourceServer;
import fun.bryce.security.annotation.EnableSecurityFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author bryce
 * @date 2018年06月21日
 * 用户统一管理系统
 */
@EnableSecurityResourceServer
@EnableSecurityFeignClients
@SpringCloudApplication
public class AdminApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AdminApplication.class, args);
    }

}
