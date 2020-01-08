/**
 * 公司：DLUT
 * 文件名：MybatisTest
 * 作者：haibing
 * 时间：2020/1/4 14:12
 * 描述：
 */

package com.hundsun.xone.ssm.test;


import com.hundsun.xone.ssm.config.RootConfig;
import com.hundsun.xone.ssm.config.WebInitConfig;
import com.hundsun.xone.ssm.entity.User;
import com.hundsun.xone.ssm.service.impl.UserServiceImpl;
import com.hundsun.xone.ssm.util.SpringContextUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
//@Configuration
/*@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})*/
/*@ContextConfiguration(classes = {RootConfig.class})*/
@PropertySource(value = {"classpath:config/application.properties"})
@ComponentScan(basePackages = "com.hundsun.xone.ssm")
@Import(WebInitConfig.class)
public class MybatisTest {

    private static final Logger LOGGER = LogManager.getLogger(MybatisTest.class);

    @Resource
    private UserServiceImpl userService = null;

    private static ApplicationContext context = SpringContextUtil.getApplicationContext();

    @Before
    public void before(){
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(beanDefinitionNames.length);
        for (String beanName : beanDefinitionNames) {
            System.out.println("beanName: " + beanName);
        }
       /* context = new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        if (userService == null){
            userService = context.getBean(UserServiceImpl.class);
            LOGGER.debug("获取userService bean成功");
        }*/
    }

    @Test
    public void testQueryUserById(){
        String userId = "202001050001";

        User user = userService.queryUserById(userId);

        System.out.println(user.toString());
    }
}
