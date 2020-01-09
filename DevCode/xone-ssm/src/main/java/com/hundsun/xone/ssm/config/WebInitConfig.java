/**
 * 公司：DLUT
 * 文件名：WebInitConfig
 * 作者：haibing
 * 时间：2020/1/7 20:35
 * 描述：
 */

package com.hundsun.xone.ssm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitConfig implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //首先来加载 SpringMVC 的配置文件
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        servletContext.setInitParameter("encode", "UTF-8");
        servletContext.setRequestCharacterEncoding("UTF-8");
        servletContext.setResponseCharacterEncoding("UTF-8");
        servletContext.createFilter(CharacterEncodingFilter.class);
        servletContext.createListener(ContextLoaderListener.class);
        servletContext.setSessionTimeout(60000);


        ctx.register(SpringMVCConfig.class, RootConfig.class, SpringConfig.class);
        // 添加 DispatcherServlet
        ServletRegistration.Dynamic springmvc = servletContext.addServlet("springmvc", new DispatcherServlet(ctx));
        // 给 DispatcherServlet 添加路径映射
        springmvc.addMapping("/");
        // 给 DispatcherServlet 添加启动时机
        springmvc.setLoadOnStartup(1);

        /**
         * 一旦配置就会报找不到applicationContext.xml
         */
       /* servletContext.addListener(contextLoaderListener);
        servletContext.addListener(introspectorCleanupListener);
        servletContext.addFilter("encodingFilter", characterEncodingFilter);*/
    }

}
