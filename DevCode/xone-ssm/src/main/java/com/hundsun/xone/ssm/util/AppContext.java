/**
 * 公司：DLUT
 * 文件名：AppContext
 * 作者：haibing
 * 时间：2020/1/4 15:59
 * 描述：
 */

package com.hundsun.xone.ssm.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppContext implements ApplicationContextAware {

    private static ApplicationContext APPCONTEXT = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (APPCONTEXT == null) {
            APPCONTEXT = applicationContext;
        }
    }

    public static ApplicationContext getAppContext(){
        return APPCONTEXT;
    }

    public static <T> T getBean(Class<T> clazz){
        return APPCONTEXT.getBean(clazz);
    }
}
