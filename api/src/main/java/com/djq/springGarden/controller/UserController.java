package com.djq.springGarden.controller;

import java.util.ArrayList;
import java.util.List;

import com.djq.springGarden.util.JwtUtil;
import org.springframework.web.bind.annotation.*;
import com.djq.springGarden.entity.User;
import com.djq.springGarden.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.djq.springGarden.vo.ResultVO;

import javax.annotation.Resource;


/**
 * 用户Controller
 *
 * @author duanjiaqi
 */
@Api(tags = "用户控制器")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 查询用户列表
     */
    @GetMapping("/list")
    @ApiOperation("查询用户列表")
    public ResultVO<Map<String, Object>> list(User user,
                                              @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.select(user);
        map.put("total", list.size());
        map.put("list", list);
        return ResultVO.ok(map, "查询成功");
    }

    /**
     * 获取用户详细信息
     */
    @ApiOperation("获取用户详细信息")
    @GetMapping(value = "/searchOne")
    public ResultVO<User> searchOne(User user) {
        return ResultVO.ok(userService.selectOne(user));
    }

    /**
     * 新增用户
     */
    @ApiOperation("新增用户")
    @PostMapping("/add")
    public ResultVO<User> add(User user) {
        return userService.insertUser(user) > 0 ? ResultVO.ok("新增成功") : ResultVO.error("新增失败");
    }

    /**
     * 修改用户
     */
    @ApiOperation("修改用户")
    @PostMapping("/update")
    public ResultVO<User> edit(User user) {
        return userService.updateUser(user) > 0 ? ResultVO.ok("更新成功") : ResultVO.error("更新失败");
    }

    /**
     * 删除用户
     */
    @ApiOperation("删除用户")
    @PostMapping("/delete")
    public ResultVO<User> remove(@RequestParam(value = "id") Integer id) {
        return userService.deleteUserById(id) > 0 ? ResultVO.ok("删除成功") : ResultVO.error("删除失败");
    }


    @PostMapping("/login")
    @ApiOperation("登录")
    public ResultVO<Object> login(@RequestParam(value = "username") String username,
                                  @RequestParam(value = "password") String password) {

        User user = new User();
        user.setName(username);
        user.setPassword(password);
        Map<String, Object> map = new HashMap<>();
        User login = userService.login(user);
        if (login == null) {
            return ResultVO.error("账号或者密码错误");
        }
        //添加token
        map.put("token", JwtUtil.createToken());
        //放入用户信息
        Map<String, String> userResult = new HashMap<>();
        userResult.put("username", user.getName());
        //查询用户的角色信息
        if (login.getRoleId() == 1) {
            userResult.put("role", "admin");
        } else if (login.getRoleId() == 2) {
            userResult.put("role", "visitor");
        } else if (login.getRoleId() == 3) {
            userResult.put("role", "user");
        }
        map.put("user", userResult);
        return ResultVO.ok(map, "登录成功");
    }


    @ApiOperation("注册账号")
    @PostMapping("/register")
    public ResultVO<User> register(User user) {
        //该接口只能注册普通用户
        user.setRoleId(3);

        //账号名不能重复和手机号码不能重复
        User userNew = new User();
        userNew.setTelphone(user.getTelphone());
        userNew.setName(user.getName());
        List<User> select = userService.select(userNew);
        if (select != null && select.size() >= 1) {
            return ResultVO.error("账号名或者手机号重复");
        }
        int register = userService.register(user);
        if (register <= 0) {
            return ResultVO.error("注册失败");
        }
        return ResultVO.ok("注册成功");
    }


    @ApiOperation("退出登录")
    @PostMapping("/loginOut")
    public ResultVO<User> loginOut(User user) {

        return ResultVO.ok("注册成功");
    }
}
