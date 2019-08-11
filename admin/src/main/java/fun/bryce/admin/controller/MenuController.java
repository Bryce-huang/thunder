package fun.bryce.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import fun.bryce.log.annotation.SysLog;
import fun.bryce.admin.service.SysMenuService;
import fun.bryce.api.dto.MenuTree;
import fun.bryce.api.entity.SysMenu;
import fun.bryce.api.vo.MenuVO;
import fun.bryce.api.vo.TreeUtil;
import fun.bryce.core.constant.CommonConstants;
import fun.bryce.core.util.R;
import fun.bryce.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author bryce
 * @date 2019/07/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuController
{
    private final SysMenuService sysMenuService;

    /**
     * 返回当前用户的树形菜单集合
     *
     * @return 当前用户的树形菜单
     */
    @GetMapping
    public R getUserMenu()
    {
        // 获取符合条件的菜单
        Set<MenuVO> all = new HashSet<>();
        SecurityUtils.getRoles()
                .forEach(roleId -> all.addAll(sysMenuService.getMenuByRoleId(roleId)));
        List<MenuTree> menuTreeList = all.stream()
                .filter(menuVo -> CommonConstants.MENU.equals(menuVo.getType()))
                .map(MenuTree::new)
                .sorted(Comparator.comparingInt(MenuTree::getSort))
                .collect(Collectors.toList());
        return R.success(TreeUtil.buildByLoop(menuTreeList, -1));
    }

    /**
     * 返回树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    public R getTree()
    {
        return R.success(TreeUtil.buildTree(sysMenuService.list(Wrappers.emptyWrapper()), -1));
    }

    /**
     * 返回角色的菜单集合
     *
     * @param roleId 角色ID
     * @return 属性集合
     */
    @GetMapping("/tree/{roleId}")
    public List getRoleTree(@PathVariable Integer roleId)
    {
        return sysMenuService.getMenuByRoleId(roleId)
                .stream()
                .map(MenuVO::getMenuId)
                .collect(Collectors.toList());
    }

    /**
     * 通过ID查询菜单的详细信息
     *
     * @param id 菜单ID
     * @return 菜单详细信息
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id)
    {
        return R.success(sysMenuService.getById(id));
    }

    /**
     * 新增菜单
     *
     * @param sysMenu 菜单信息
     * @return success/false
     */
    @SysLog("新增菜单")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_menu_add')")
    public R save(@Valid @RequestBody SysMenu sysMenu)
    {
        return R.success(sysMenuService.save(sysMenu));
    }

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return success/false
     */
    @SysLog("删除菜单")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_menu_del')")
    public R removeById(@PathVariable Integer id)
    {
        return sysMenuService.removeMenuById(id);
    }

    /**
     * 更新菜单
     *
     * @param sysMenu
     * @return
     */
    @SysLog("更新菜单")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_menu_edit')")
    public R update(@Valid @RequestBody SysMenu sysMenu)
    {
        return R.success(sysMenuService.updateMenuById(sysMenu));
    }

}
