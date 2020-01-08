/**
 * 公司：DLUT
 * 文件名：ConfigTest
 * 作者：haibing
 * 时间：2020/1/6 19:21
 * 描述：
 */

package com.hundsun.xone.ssm.test;

import com.hundsun.xone.ssm.config.RootConfig;
import com.hundsun.xone.ssm.config.WebInitConfig;
import com.hundsun.xone.ssm.dao.UserDAO;
import com.hundsun.xone.ssm.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@Configuration("classpath:config/log4j2.xml")
/*@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})*/
@ContextConfiguration(classes = {RootConfig.class})
@ComponentScan(basePackages = {"com.hundsun.xone.ssm"})
@Import(WebInitConfig.class)
public class ConfigTest {

    @Autowired
    private UserDAO userDAO;


    @Test
    public void test(){
        String username = "顾海兵";

        User user = userDAO.selectUserByUserName(username);

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ConfigTest.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println(beanDefinitionNames.length);
        for (String beanName : beanDefinitionNames) {
            System.out.println("beanName: " + beanName);
        }
      /*  CacheUtil.getCache().set("顾海兵", user);
        Object testValue = CacheUtil.getCache().get("顾海兵");*/

        System.out.println(user.toString());
//        System.out.println(testValue.toString());
    }
}
