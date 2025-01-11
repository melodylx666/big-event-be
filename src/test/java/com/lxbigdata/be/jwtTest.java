package com.lxbigdata.be;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

/**
 * ClassName: jwtTest
 * Package: com.lxbigdata.be
 * Description:
 *
 * @author lx
 * @version 1.0
 */
public class jwtTest {
    @Test
    // 测试jwt
    public void useJwt() {
        var claims = new HashMap<String , Object>();
        claims.put("id", 1);
        claims.put("username", "zhangsan");
        //一共三部分：header, payload, signature
        String token = JWT
                .create()
                .withClaim("user",claims) //添加payload
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .sign(Algorithm.HMAC256("my_secret"));
        System.out.println(token);
    }

    @Test
    public void testJwt() {
        //如果token被篡改，或者过期，则会抛出异常
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6InpoYW5nc2FuIn0sImV4cCI6MTczNjU5Mjg4MH0.A0LF594WBx2s8yIZ1DjZG3A4x17vweSbxGb_C3MM7Bs";
        JWT.require(Algorithm.HMAC256("my_secret"))
                .build()//创建验证器
                .verify(token)//验证token
                .getClaim("user") //获取payload中key为user的部分
                .asMap()
                .forEach((k,v) -> System.out.println(k + ":" + v));

    }
}
