package fun.bryce.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.bryce.api.entity.SysOauthClientDetails;
import fun.bryce.core.util.R;
import fun.bryce.log.annotation.SysLog;
import fun.bryce.admin.service.SysOauthClientDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bryce
 * @since 2018-05-15
 */
@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class OauthClientDetailsController
{
    private final SysOauthClientDetailsService sysOauthClientDetailsService;

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysOauthClientDetails
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id)
    {
        return R.success(sysOauthClientDetailsService.getById(id));
    }


    /**
     * 简单分页查询
     *
     * @param page                  分页对象
     * @param sysOauthClientDetails 系统终端
     * @return
     */
    @GetMapping("/page")
    public R getOauthClientDetailsPage(Page page, SysOauthClientDetails sysOauthClientDetails)
    {
        return R.success(sysOauthClientDetailsService.page(page, Wrappers.query(sysOauthClientDetails)));
    }

    /**
     * 添加
     *
     * @param sysOauthClientDetails 实体
     * @return success/false
     */
    @SysLog("添加终端")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_client_add')")
    public R add(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails)
    {
        return R.success(sysOauthClientDetailsService.save(sysOauthClientDetails));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @SysLog("删除终端")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_client_del')")
    public R removeById(@PathVariable String id)
    {
        return R.success(sysOauthClientDetailsService.removeClientDetailsById(id));
    }

    /**
     * 编辑
     *
     * @param sysOauthClientDetails 实体
     * @return success/false
     */
    @SysLog("编辑终端")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_client_edit')")
    public R update(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails)
    {
        return R.success(sysOauthClientDetailsService.updateClientDetailsById(sysOauthClientDetails));
    }
}
