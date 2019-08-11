package fun.bryce.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.bryce.api.entity.SysLog;
import fun.bryce.admin.mapper.SysLogMapper;
import fun.bryce.admin.service.SysLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService
{

}
