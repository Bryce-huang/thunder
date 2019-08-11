
package fun.bryce.log.event;

import fun.bryce.api.entity.SysLog;
import fun.bryce.api.feign.RemoteLogService;
import fun.bryce.core.constant.SecurityConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;


/**
 * @author bryce
 * 异步监听日志事件
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener
{
    private final RemoteLogService remoteLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event)
    {
        SysLog sysLog = (SysLog) event.getSource();
        remoteLogService.saveLog(sysLog, SecurityConstants.FROM_IN);
    }
}
