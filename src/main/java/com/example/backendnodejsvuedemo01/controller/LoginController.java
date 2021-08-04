package com.example.backendnodejsvuedemo01.controller;

import com.example.backendnodejsvuedemo01.dao.UserDao;
import com.example.backendnodejsvuedemo01.pojo.User;
import com.example.backendnodejsvuedemo01.result.Result;
import com.example.backendnodejsvuedemo01.service.UserService;
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

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser,
                        HttpSession session){
        //对html标签进行转义，防止XSS攻击
        String username = requestUser.getUsername();
        String password = requestUser.getPassword();
        username = HtmlUtils.htmlEscape(username);

        //实际上在 controller 里写这么多逻辑是不合理的，要尽量封装到 service 里面去。
//        if(!Objects.equals("admin",username) || !Objects.equals("123456",password)){
//            String message = "账号或密码错误！";
//            System.out.println("账号或密码错误！");
//            return new Result(400);
//        }else {
//            return new Result(200);
//        }
        User user = userService.get(username,password);
        if(user == null){
            return new Result(400);
        }else {
            //保存用户user的session在session变量中
            session.setAttribute("user",user);
            return new Result(200);
        }
    }
}
