package com.example.backendnodejsvuedemo01.interceptor;

import com.example.backendnodejsvuedemo01.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author Nick
 * @Classname LoginInterceptor
 * @Date 2021/8/4 10:56
 * @Description 登录拦截器
 * 直接继承拦截器HandlerInterceptor的接口，然后实现 preHandle 方法
 * preHandle 方法里的代码会在访问需要拦截的页面时执行
 *
 * problem：访问login页面仍然需要跳转，造成多次重定向问题
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        //此处可以添加想要拦截的路径，拦截列表
        String[] requireAuthPages = new String[]{
                "index",
        };

        //获取访问的简洁版路径http://127.0.0.1:8443/index  ======>      index
        //去除uri路径中context Path（默认初始路径）+/ ，剩余用户访问的路径部分  给page变量
        String requestURI = request.getRequestURI();
        requestURI = StringUtils.remove(requestURI,contextPath+"/");
        String page = requestURI;


        //此处正确访问了index路径（被拦截）后，进行user对比，正确就放行，不正确就跳转回login界面
        if(beganingWith(page,requireAuthPages)){
            //查看session中有没有用户user的信息，有就
            User user = (User) session.getAttribute("user");
            //没有查询到用户，则跳转回login页面
            if(user == null){
                response.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    //判断访问路径page是否存在拦截列表中，存在就返回true再进行拦截手段，不存在就放行
    private boolean beganingWith(String page, String[] requireAuthPages) {
        boolean result = false;
        for (String requireAuthPage: requireAuthPages){
            //判断page的是不是以requireAuthPage开头的字符串
            if (StringUtils.startsWith(page, requireAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
