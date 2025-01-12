package com.lxbigdata.be.UtilsTest;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * ClassName: ThreadLocalTest
 * Package: com.lxbigdata.be.UtilsTest
 * Description:
 *
 * @author lx
 * @version 1.0
 */
public class ThreadLocalTest {
    @Test
    public void testThreadLocal(){
        var tl = new ThreadLocal<String>();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            tl.set("tl01");
            return tl.get();
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            tl.set("tl02");
            return tl.get();
        });
        CompletableFuture<String> ans = future1.thenCombine(future2, (s1, s2) -> {
            return s1 + s2;
        });
        assert ans.join().equals("tl01tl02");

    }
}
