

package fun.bryce.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author bryce
 * @date 2019-08-01
 * <p>
 * 网关应用
 */
@SpringCloudApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);

	}
}
