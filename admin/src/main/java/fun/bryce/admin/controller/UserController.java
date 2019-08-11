package fun.bryce.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.bryce.log.annotation.SysLog;
import fun.bryce.admin.service.SysUserService;
import fun.bryce.api.dto.UserDTO;
import fun.bryce.api.entity.SysUser;
import fun.bryce.core.util.R;
import fun.bryce.security.annotation.Inner;
import fun.bryce.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author bryce
 * @date 2019/07/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController
{
    private final SysUserService userService;

    /**
     * 获取当前用户全部信息
     *
     * @return 用户信息
     */
    @GetMapping(value = {"/info"})
    public R info()
    {
        String username = SecurityUtils.getUser().getUsername();
        SysUser user = userService.getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username));
        if (user == null)
        {
            R<Boolean> r = new R<>();
            r.setData(Boolean.FALSE);
            r.setMsg("获取当前用户信息失败");
            return r;
        }
        return R.success(userService.getUserInfo(user));
    }

    /**
     * 获取指定用户全部信息
     *
     * @return 用户信息
     */
    @Inner
    @GetMapping("/info/{username}")
    public R info(@PathVariable String username)
    {
        SysUser user = userService.getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username));
        if (user == null)
        {
            R<Boolean> r = new R<>();
            r.setData(Boolean.FALSE);
            r.setMsg(String.format("用户信息为空 %s", username));
            return r;
        }
        return R.success(userService.getUserInfo(user));
    }

    /**
     * 通过ID查询用户信息
     *
     * @param id ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public R user(@PathVariable Integer id)
    {
        return R.success(userService.getUserVoById(id));
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return
     */
    @GetMapping("/details/{username}")
    public R user(@PathVariable String username)
    {
        SysUser condition = new SysUser();
        condition.setUsername(username);
        return R.success(userService.getOne(new QueryWrapper<>(condition)));
    }

    /**
     * 删除用户信息
     *
     * @param id ID
     * @return R
     */
    @SysLog("删除用户信息")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_user_del')")
    public R userDel(@PathVariable Integer id)
    {
        SysUser sysUser = userService.getById(id);
        return R.success(userService.removeUserById(sysUser));
    }

    /**
     * 添加用户
     *
     * @param userDto 用户信息
     * @return success/false
     */
    @SysLog("添加用户")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_user_add')")
    public R user(@RequestBody UserDTO userDto)
    {
        return R.success(userService.saveUser(userDto));
    }

    /**
     * 更新用户信息
     *
     * @param userDto 用户信息
     * @return R
     */
    @SysLog("更新用户信息")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_user_edit')")
    public R updateUser(@Valid @RequestBody UserDTO userDto)
    {
        return R.success(userService.updateUser(userDto));
    }

    /**
     * 分页查询用户
     *
     * @param page    参数集
     * @param userDTO 查询参数列表
     * @return 用户集合
     */
    @GetMapping("/page")
    public R getUserPage(Page page, UserDTO userDTO)
    {
        return R.success(userService.getUserWithRolePage(page, userDTO));
    }

    /**
     * 修改个人信息
     *
     * @param userDto userDto
     * @return success/false
     */
    @SysLog("修改个人信息")
    @PutMapping("/edit")
    public R updateUserInfo(@Valid @RequestBody UserDTO userDto)
    {
        return userService.updateUserInfo(userDto);
    }

    /**
     * @param username 用户名称
     * @return 上级部门用户列表
     */
    @GetMapping("/ancestor/{username}")
    public R listAncestorUsers(@PathVariable String username)
    {
        return R.success(userService.listAncestorUsersByUsername(username));
    }
}
