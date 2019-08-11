

package fun.bryce.api.feign.factory;

import fun.bryce.api.feign.RemoteLogService;
import fun.bryce.api.feign.fallback.RemoteLogServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author bryce
 * @date 2019/07/1
 */
@Component
public class RemoteLogServiceFallbackFactory implements FallbackFactory<RemoteLogService> {

	@Override
	public RemoteLogService create(Throwable throwable) {
		RemoteLogServiceFallbackImpl remoteLogServiceFallback = new RemoteLogServiceFallbackImpl();
		remoteLogServiceFallback.setCause(throwable);
		return remoteLogServiceFallback;
	}
}
