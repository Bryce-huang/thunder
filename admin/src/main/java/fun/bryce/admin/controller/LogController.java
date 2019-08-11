package fun.bryce.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.bryce.admin.service.SysLogService;
import fun.bryce.api.entity.SysLog;
import fun.bryce.core.util.R;
import fun.bryce.security.annotation.Inner;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/log")
public class LogController
{
    private final SysLogService sysLogService;

    /**
     * 简单分页查询
     *
     * @param page   分页对象
     * @param sysLog 系统日志
     * @return
     */
    @GetMapping("/page")
    public R getLogPage(Page page, SysLog sysLog)
    {
        return R.success(sysLogService.page(page, Wrappers.query(sysLog)));
    }

    /**
     * 删除日志
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_log_del')")
    public R removeById(@PathVariable Long id)
    {
        return R.success(sysLogService.removeById(id));
    }

    /**
     * 插入日志
     *
     * @param sysLog 日志实体
     * @return success/false
     */
    @Inner
    @PostMapping
    public R save(@Valid @RequestBody SysLog sysLog)
    {
        return R.success(sysLogService.save(sysLog));
    }

}
