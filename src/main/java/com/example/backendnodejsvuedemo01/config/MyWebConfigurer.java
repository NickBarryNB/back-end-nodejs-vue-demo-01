package com.example.backendnodejsvuedemo01.config;

import com.example.backendnodejsvuedemo01.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Nick
 * @Classname MyWebConfigurer
 * @Date 2021/8/4 11:34
 * @Description 装配拦截器
 */
@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {
    @Bean
    public LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //对所有路径添加拦截器，除了index.html,因为如果不排除index.html的拦截，就会被拦截器拦截，因为拦截器
        //设置为开头为index的路径会被拦截，所以需要取消对index.html的拦截
        //对登录和登出不作拦截
        registry.addInterceptor(getLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html")
                .excludePathPatterns("/api/logout")
                .excludePathPatterns("/api/login");
    }

    //解决跨域问题，此处允许所有请求跨域
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedHeaders("*")
//                .allowedMethods("*");
//    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 在 allowCredentials(true) ，即允许跨域使用 cookie 的情况下，
        // allowedOrigins() 不能使用通配符 *，这也是出于安全上的考虑
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*");
    }

    //把上传图片的url和本地路径关联起来
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "d:/WorkSpace/nodejs/img/");
    }
}
