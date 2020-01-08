/**
 * 公司：DLUT
 * 文件名：SpringConfig
 * 作者：haibing
 * 时间：2020/1/7 20:12
 * 描述：
 */

package com.hundsun.xone.ssm.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.util.IntrospectorCleanupListener;

/**
 * 代替 spring-mybatis.xml文件，相关bean都放在RootConfig类中
 * @ComponentScan 注解表示配置包扫描，里边的属性和 xml 配置中的属性都是一一对应的，
 * useDefaultFilters 表示使用默认的过滤器，然后又除去 Controller 注解，
 * 即在 Spring 容器中扫描除了 Controller 之外的其他所有 Bean 。
 */
@Configuration
@ComponentScan(basePackages = "com.hundsun.xone.ssm.service", useDefaultFilters = true,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)})
public class SpringConfig {

    /**
     * 配置监听器监听web容器
     * @return
     */
    @Bean
    @Primary
    public ContextLoaderListener contextLoaderListener(){
        return new ContextLoaderListener();
    }

    /**
     * 防止Spring内存溢出监听器
     * @return
     */
    @Bean
    public IntrospectorCleanupListener cleanupListener(){
        return new IntrospectorCleanupListener();
    }

    @Bean("encodingFilter")
    public CharacterEncodingFilter encodingFilter(){
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        return encodingFilter;
    }
}
