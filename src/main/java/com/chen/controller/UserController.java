package com.chen.controller;

import com.chen.comm.Result;
import com.chen.comm.consts.SessionConst;
import com.chen.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册接口
     * 用户传递用户名和密码两个数据
     * 服务器判断用户名是否已经存在
     * 如果不存在  那么新建用户 如果存在  告诉用户用户名已经存在 不允许注册
     */
    @RequestMapping("/register")
    @ResponseBody
    public Result<Object> register(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        //判断用户名是否存在
        if (userService.isUserExisted(username)) {
            return new Result<>(false, 0, "用户名已经存在", null);
        }
        //如果用户名不存在  那么添加用户
        boolean insertStatus = userService.addUser(username, password);
        if (insertStatus) {
            return new Result<>(true, 0, "注册成功", null);
        } else {
            return new Result<>(false, 0, "注册失败", null);
        }

    }

    @RequestMapping("/login")
    @ResponseBody
    public Result<Object> login(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpServletRequest request) {
        int uid = userService.login(username, password);
        if (uid != 0) {
            request.getSession().setAttribute(SessionConst.UID.getValue(), uid);
            return new Result<>(true, 0, "登录成功", null);
        }
        return new Result<>(false, 0, "登录失败", null);

    }

    @RequestMapping("/isUserLogin")
    @ResponseBody
    public Result<Object> isUserLogin(HttpServletRequest request) {
        Integer uid = (Integer) request.getSession().getAttribute(SessionConst.UID.getValue());
        if (uid != null && !uid.equals(0)) {
            return new Result<>(true, 0, "用户已经登录", null);

        }
        return new Result<>(false, 0, "用户未登录", null);
    }


}
