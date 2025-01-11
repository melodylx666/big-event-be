package com.lxbigdata.be.config;

import com.lxbigdata.be.interceptors.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebConfig
 * Package: com.lxbigdata.be.config
 * Description:
 *
 * @author lx
 * @version 1.0
 */
@Configuration //同样注入IOC容器中
public class WebConfig implements WebMvcConfigurer {
    //因为前面已经将LoginInterceptor放到Spring容器中(Components)，所以这里可以直接注入
    @Resource
    private LoginInterceptor loginInterceptor;

    /**
     * 注册拦截器,对两个路径完全放行，其余的进行拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/user/login", "/user/register");
    }
}
