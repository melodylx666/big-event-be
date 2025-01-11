package com.lxbigdata.be.interceptors;

import com.lxbigdata.be.pojo.Result;
import com.lxbigdata.be.utils.JwtUtil;
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
            return true;
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }
    }
}
