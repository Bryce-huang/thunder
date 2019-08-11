package fun.bryce.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.bryce.api.entity.SysDict;
import fun.bryce.admin.mapper.SysDictMapper;
import fun.bryce.admin.service.SysDictService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService
{

}
