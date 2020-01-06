/**
 * 公司：DLUT
 * 文件名：CacheTest
 * 作者：haibing
 * 时间：2020/1/4 19:38
 * 描述：
 */

package com.hundsun.xone.ssm.test;

import com.hundsun.xone.ssm.cache.CacheUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-redis.xml")
public class JedisTest {

    /**
     * 测试基本操作
     */
    @Test
    public void execute() {
        CacheUtil.getCache().set("test-jedis", "test-value");
        Object testValue = CacheUtil.getCache().get("test-jedis");
        System.out.println(String.valueOf(testValue));
    }
}
