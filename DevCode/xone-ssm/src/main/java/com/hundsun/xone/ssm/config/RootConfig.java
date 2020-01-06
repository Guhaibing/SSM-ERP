/**
 * 公司：DLUT
 * 文件名：RootConfig
 * 作者：haibing
 * 时间：2020/1/6 15:19
 * 描述：
 */

package com.hundsun.xone.ssm.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 将xml配置文件加载bean方式改换成类加载bean方式
 * 1.将mysql的数据源配置方法该换
 */
@Configuration
public class RootConfig implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }



}
