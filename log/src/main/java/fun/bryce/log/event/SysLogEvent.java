

package fun.bryce.log.event;

import fun.bryce.api.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author bryce
 * 系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {

	public SysLogEvent(SysLog source) {
		super(source);
	}
}
