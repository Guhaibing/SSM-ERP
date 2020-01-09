/**
 * 公司：DLUT
 * 文件名：SpringMVCConfig
 * 作者：haibing
 * 时间：2020/1/7 20:18
 * 描述：
 */

package com.hundsun.xone.ssm.config;


import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.hundsun.xone.ssm.config.interceptor.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 代替 spring-mvc.xml文件
 * 在 SpringMVC 中添加其他的额外配置,
 * 视图解析器、JSON 解析、文件上传......等等，如果都不需要配置的话，这样就可以了。
 * 可以实现接口WebMvcConfigurer， 也可以继承 WebMvcConfigurationSupport 类
 */
@Configuration
//@EnableWebMvc
// 如果想保持Spring Boot MVC原本的配置（自动配置）并且又想增加自己的配置，
// 那么add your own @Configuration class of type WebMvcConfigurer but without @EnableWebMvc
// 因为@EnableWebMvc中导入实现了WebMvcConfigurationSupport的默认配置类，所以下面的添加拦截器刚开始失败
//@Order(3)
@ComponentScan(basePackages = {"com.hundsun.xone.ssm.controller", "com.hundsun.xone.ssm.config.interceptor"},useDefaultFilters = false
        , includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class)})
public class SpringMVCConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LoginHandlerInterceptor handlerInterceptor;
    /**
     * 配置页面路径和.jsp文件
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/static/");
        System.out.println("加载配置文件");
        registry.addResourceHandler("/config/application.properties").addResourceLocations("classpath:config/application.properties");
    }

    /**
     * 路径映射
     * 有的时候，我们的控制器的作用仅仅只是一个跳转，就像上面小节中的控制器，里边没有任何业务逻辑，
     * 像这种情况，可以不用定义方法，可以直接通过路径映射来实现页面访问。
     * 这样就可以直接跳到这个视图层
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hello").setViewName("hello");
    }

    /**
     * SpringMVC 可以接收JSON 参数，也可以返回 JSON 参数，这一切依赖于 HttpMessageConverter。
     *
     * HttpMessageConverter 可以将一个 JSON 字符串转为 对象，也可以将一个对象转为 JSON 字符串，实际上它的底层还是依赖于具体的 JSON 库。
     *
     * 所有的 JSON 库要在 SpringMVC 中自动返回或者接收 JSON，都必须提供和自己相关的 HttpMessageConverter 。
     *
     * SpringMVC 中，默认提供了 Jackson 和 gson 的 HttpMessageConverter ，分别是：MappingJackson2HttpMessageConverter 和 GsonHttpMessageConverter 。
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        converter.setFastJsonConfig(fastJsonConfig);
        converters.add(converter);
    }

    /**
     * 启用自定义拦截器拦截指定url
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截，只允许登录或注册后，方可执行用户操作
        registry.addInterceptor(handlerInterceptor)
        .addPathPatterns("/user/*").excludePathPatterns("/user/login","/user/register");
    }
}
