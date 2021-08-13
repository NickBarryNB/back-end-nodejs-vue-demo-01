package com.example.backendnodejsvuedemo01.controller;

import com.example.backendnodejsvuedemo01.pojo.User;
import com.example.backendnodejsvuedemo01.result.Result;
import com.example.backendnodejsvuedemo01.result.ResultFactory;
import com.example.backendnodejsvuedemo01.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @Author Nick
 * @Classname LoginController
 * @Date 2021/8/3 18:32
 * @Description Controller 是对响应进行处理的部分
 */
// 这里我们设定账号是 admin，密码是 123456，
// 分别与接收到的 User 类的 username 和 password 进行比较，
// 根据结果返回不同的 Result，即不同的响应码。
// 前端如果接收到成功的响应码（200），则跳转到 /index 页面。
    // 改进成从数据中比对username和password==================================

@Controller
public class LoginController {
    @Autowired
    UserService  userService;


//    简单登录验证
//    @CrossOrigin
//    @PostMapping(value = "api/login")
//    @ResponseBody
//    public Result login(@RequestBody User requestUser,
//                        HttpSession session){
//        //对html标签进行转义，防止XSS攻击
//        String username = requestUser.getUsername();
//        String password = requestUser.getPassword();
//        username = HtmlUtils.htmlEscape(username);
//
//        //实际上在 controller 里写这么多逻辑是不合理的，要尽量封装到 service 里面去。
////        if(!Objects.equals("admin",username) || !Objects.equals("123456",password)){
////            String message = "账号或密码错误！";
////            System.out.println("账号或密码错误！");
////            return new Result(400);
////        }else {
////            return new Result(200);
////        }
//        User user = userService.get(username,password);
//        if(user == null){
//            return new Result(400);
//        }else {
//            //保存用户user的session在session变量中
//            session.setAttribute("user",user);
//            return new Result(200);
//        }
//    }

//    登录
    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User user,
                        HttpSession session){
        String username = user.getUsername();
        Subject subject = SecurityUtils.getSubject();

//        subject.getSession().setTimeout(1000);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, user.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try {
//            实际开发中，我们只需要调用一句 subject.login(usernamePasswordToken) 就可以执行验证
//            大概经过七八层调用，Shiro 通过 Realm 里我们重写的 doGetAuthenticationInfo 方法获取到了验证信息，
//            再根据我们在配置类里定义的 CredentialsMatcher（HashedCredentialsMatcher），执行doCredentialsMatch方法
            subject.login(usernamePasswordToken);
            session.setAttribute("user",user);
            return ResultFactory.buildSuccessResult(username);
        }catch (AuthenticationException e){
            String message = "账户或者密码错误！";
            return ResultFactory.buildFailResult(message);
        }
    }

//    注册
    @CrossOrigin
    @PostMapping(value = "api/register")
    @ResponseBody
    public Result register(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        boolean exist = userService.isExist(username);
        if(exist){
            String message = "用户名已被占用！请重新输入！";
            return ResultFactory.buildFailResult(message);
        }

//        生成盐，默认长度16位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
//        设置hash，设置迭代次数为2
        int times = 2;
//        得到hash后的密码
        String encodePassword = new SimpleHash("md5", password, salt, times).toString();
//        存储用户信息，包括salt和hash后的密码
        user.setSalt(salt);
        user.setPassword(encodePassword);
        userService.add(user);

        return ResultFactory.buildSuccessResult(user);
    }

    // 登出 logout
    @GetMapping(value = "api/logout")
    @ResponseBody
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        // 核心就是 subject.logout()
        subject.logout();
        String message = "登出成功";
        return ResultFactory.buildSuccessResult(message);
    }

    // 认证
    @ResponseBody
    @GetMapping(value = "api/authentication")
    public String authentication(){
        return "身份认证成功";
    }
}
