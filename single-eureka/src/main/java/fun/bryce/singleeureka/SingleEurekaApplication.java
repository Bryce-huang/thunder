package fun.bryce.singleeureka;

import com.spring4all.swagger.EnableSwagger2Doc;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author bryce
 */
@SpringBootApplication
@EnableEurekaServer
@RestController
@RequestMapping("/demo")
@EnableSwagger2Doc
public class SingleEurekaApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SingleEurekaApplication.class, args);
    }


    @ApiOperation(value = "demo服务的get方法")
    @ApiParam(value = "id参数描述", required = true)
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String get(@PathVariable Integer id)
    {
        return "得到你的id:" + id;
    }

}
