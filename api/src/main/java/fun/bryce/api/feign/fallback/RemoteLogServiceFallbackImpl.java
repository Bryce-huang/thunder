

package fun.bryce.api.feign.fallback;

import fun.bryce.api.entity.SysLog;
import fun.bryce.api.feign.RemoteLogService;
import fun.bryce.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author bryce
 * @date 2019/07/1
 */
@Slf4j
@Component
public class RemoteLogServiceFallbackImpl implements RemoteLogService {
	@Setter
	private Throwable cause;

	/**
	 * 保存日志
	 *
	 * @param sysLog 日志实体
	 * @param from   内部调用标志
	 * @return succes、false
	 */
	@Override
	public R<Boolean> saveLog(SysLog sysLog, String from) {
		log.error("feign 插入日志失败", cause);
		return null;
	}
}
