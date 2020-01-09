/**
 * 公司：DLUT
 * 文件名：LoginHandlerInterceptor
 * 作者：haibing
 * 时间：2020/1/9 12:30
 * 描述：
 */

package com.hundsun.xone.ssm.config.interceptor;

import com.hundsun.xone.ssm.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LogManager.getLogger(LoginHandlerInterceptor.class);
    /**
     * 在调用处理器之前调用该方法，如果该方法返回true则请求继续向下进行，否则请求不会继续向下进行,处理器也不会调用;
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器启动");
        LOGGER.info("请求来自："+ request.getRequestURI());
        if (request.getRequestURI().endsWith("login") || request.getRequestURI().endsWith("register")) {
            System.out.println("登录或注册不进行拦截");
            return true;
        }
        if (request.getSession().getAttribute("user") instanceof User){
            return true;
        }
        System.out.println("拦截成功");
        return false;
    }

    /**
     * 在调用完处理器后调用该方法;
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("业务逻辑处理完成");
    }

    /**
     * 只要该拦截器中的preHandle方法返回true，该方法就会被调用;
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("未进行拦截");
    }
}
