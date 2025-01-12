package com.lxbigdata.be.interceptors;

import com.lxbigdata.be.pojo.Result;
import com.lxbigdata.be.utils.JwtUtil;
import com.lxbigdata.be.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * ClassName: LoginInterceptor
 * Package: com.lxbigdata.be.interceptors
 * Description:
 *  令牌主动失效，防止拿到旧的token还能登陆
 * //思路：将当前请求携带的令牌与redis中的令牌进行比对，如果一致，则放行，否则拦截。
 * //没用的令牌(比如用户修改密码)直接在redis中删除
 * @author lx
 * @version 1.0
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token = request.getHeader("Authorization");
        try{
            //从redis中获取相同的token
            var redisToken = stringRedisTemplate.opsForValue().get(token);
            if(redisToken == null){
                throw new RuntimeException("令牌失效，请重新登录");
            }
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
