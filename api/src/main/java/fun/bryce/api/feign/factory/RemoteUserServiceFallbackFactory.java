

package fun.bryce.api.feign.factory;

import fun.bryce.api.feign.RemoteUserService;
import fun.bryce.api.feign.fallback.RemoteUserServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author bryce
 * @date 2019/07/1
 */
@Component
public class RemoteUserServiceFallbackFactory implements FallbackFactory<RemoteUserService> {

	@Override
	public RemoteUserService create(Throwable throwable) {
		RemoteUserServiceFallbackImpl remoteUserServiceFallback = new RemoteUserServiceFallbackImpl();
		remoteUserServiceFallback.setCause(throwable);
		return remoteUserServiceFallback;
	}
}
