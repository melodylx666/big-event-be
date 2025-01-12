package com.lxbigdata.be.interceptors;

import com.lxbigdata.be.pojo.Result;
import com.lxbigdata.be.utils.JwtUtil;
import com.lxbigdata.be.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * ClassName: LoginInterceptor
 * Package: com.lxbigdata.be.interceptors
 * Description:
 *
 * @author lx
 * @version 1.0
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token = request.getHeader("Authorization");
        try{
            var stringObjectMap = JwtUtil.parseToken(token);
            //将用户信息存入ThreadLocal,这里一个用户线程只用ThreadLocal存储了一个变量
            ThreadLocalUtil.set(stringObjectMap);
            return true;
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除ThreadLocal中的数据,防止内存泄露
        ThreadLocalUtil.remove();
    }
}
