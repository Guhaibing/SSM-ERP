/**
 * 公司：DLUT
 * 文件名：RedissionTest
 * 作者：haibing
 * 时间：2020/1/4 19:40
 * 描述：
 */

package com.hundsun.xone.ssm.test;

import com.hundsun.xone.ssm.cache.CacheUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-redisson.xml")
public class RedissonTest {

    /**
     * 测试分布式锁
     */
    @Test
    public void execute() {
        // 加锁
        CacheUtil.getLockManager().lock("test-jedis");
        // 解锁
        CacheUtil.getLockManager().unlock("test-jedis");
    }

}

