package com.example.backendnodejsvuedemo01.interceptor;

import com.example.backendnodejsvuedemo01.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response,
//                             Object handler) throws Exception {
//        HttpSession session = request.getSession();
//        String contextPath = session.getServletContext().getContextPath();
//        //此处可以添加想要拦截的路径，拦截列表
//        String[] requireAuthPages = new String[]{
//                "index",
//        };
//
//        //获取访问的简洁版路径http://127.0.0.1:8443/index  ======>      index
//        //去除uri路径中context Path（默认初始路径）+/ ，剩余用户访问的路径部分  给page变量
//        String requestURI = request.getRequestURI();
//        requestURI = StringUtils.remove(requestURI,contextPath+"/");
//        String page = requestURI;
//
//
//        //此处正确访问了index路径（被拦截）后，进行user对比，正确就放行，不正确就跳转回login界面
//        if(beginingWith(page,requireAuthPages)){
//            //查看session中有没有用户user的信息，有就
//            User user = (User) session.getAttribute("user");
//            //没有查询到用户，则跳转回login页面
//            if(user == null){
//                response.sendRedirect("login");
//                return false;
//            }
//        }
//        return true;
//    }

    //判断访问路径page是否存在拦截列表中，存在就返回true再进行拦截手段，不存在就放行
//    private boolean beginingWith(String page, String[] requireAuthPages) {
//        boolean result = false;
//        for (String requireAuthPage: requireAuthPages){
//            //判断page的是不是以requireAuthPage开头的字符串
//            if (StringUtils.startsWith(page, requireAuthPage)) {
//                result = true;
//                break;
//            }
//        }
//        return result;
//    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 跨域情况下会先发出一个 options 请求试探，这个请求是不带 cookie 信息的，
        // 所以 shiro 无法获取到 sessionId，将导致认证失败
        // 放行 options 请求，否则无法让前端带上自定义的 header 信息，导致 sessionID 改变，shiro 验证失败
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        Subject subject = SecurityUtils.getSubject();
        // 使用 shiro 验证
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            return false;
        }
        return true;
    }
}
