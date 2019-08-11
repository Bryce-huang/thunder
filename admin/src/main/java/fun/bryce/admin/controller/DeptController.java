package fun.bryce.admin.controller;

import fun.bryce.admin.service.SysDeptService;
import fun.bryce.api.entity.SysDept;
import fun.bryce.core.util.R;
import fun.bryce.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dept")
public class DeptController
{
    private final SysDeptService sysDeptService;

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysDept
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id)
    {
        return R.success(sysDeptService.getById(id));
    }


    /**
     * 返回树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    public R listDeptTrees()
    {
        return R.success(sysDeptService.listDeptTrees());
    }

    /**
     * 返回当前用户树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/user-tree")
    public R listCurrentUserDeptTrees()
    {
        return R.success(sysDeptService.listCurrentUserDeptTrees());
    }

    /**
     * 添加
     *
     * @param sysDept 实体
     * @return success/false
     */
    @SysLog("添加部门")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_dept_add')")
    public R save(@Valid @RequestBody SysDept sysDept)
    {
        return R.success(sysDeptService.saveDept(sysDept));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @SysLog("删除部门")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_dept_del')")
    public R removeById(@PathVariable Integer id)
    {
        return R.success(sysDeptService.removeDeptById(id));
    }

    /**
     * 编辑
     *
     * @param sysDept 实体
     * @return success/false
     */
    @SysLog("编辑部门")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_dept_edit')")
    public R update(@Valid @RequestBody SysDept sysDept)
    {
        sysDept.setUpdateTime(LocalDateTime.now());
        return R.success(sysDeptService.updateDeptById(sysDept));
    }
}
