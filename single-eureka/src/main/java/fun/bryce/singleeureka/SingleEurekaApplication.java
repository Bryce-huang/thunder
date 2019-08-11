package fun.bryce.singleeureka;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author bryce
 */
@SpringBootApplication
@EnableEurekaServer
@EnableSwagger2Doc
public class SingleEurekaApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SingleEurekaApplication.class, args);
    }

}
