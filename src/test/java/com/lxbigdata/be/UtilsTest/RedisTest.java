package com.lxbigdata.be.UtilsTest;

import com.lxbigdata.be.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: RedisTest
 * Package: com.lxbigdata.be.UtilsTest
 * Description:
 *
 * @author lx
 * @version 1.0
 */
@SpringBootTest //测试类需要添加这个注解，则会首先初始化Spring容器，想要什么服务直接注入即可
public class RedisTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testSetAndGet(){
        //向redis中存储一个k-v
        var ops = stringRedisTemplate.opsForValue();
        ops.set("username","zhangsan");
        assert ops.get("username").equals("zhangsan");
    }
    @Test
    public void testSetAndGetWithTimeOut(){
        var ops = stringRedisTemplate.opsForValue();
        ops.set("username","zhangsan",15, TimeUnit.SECONDS);
        assert ops.get("username").equals("zhangsan");
        try {
            Thread.sleep(15000);
            assert ops.get("username") == null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
