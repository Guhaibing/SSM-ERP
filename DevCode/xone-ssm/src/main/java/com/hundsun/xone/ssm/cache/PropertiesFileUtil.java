/**
 * 公司：DLUT
 * 文件名：PropertiesFileUtil
 * 作者：haibing
 * 时间：2020/1/4 19:28
 * 描述：
 */

package com.hundsun.xone.ssm.cache;

import java.util.HashMap;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 资源文件读取工具
 *
 * @author Ben.
 *
 */
public class PropertiesFileUtil {

    private static Logger logger = LogManager.getLogger(PropertiesFileUtil.class);
    // 当打开多个资源文件时，缓存资源文件
    private static HashMap<String, PropertiesConfiguration> configMap = new HashMap<String, PropertiesConfiguration>();
    // 默认资源文件名称
    private static final String NAME = "config.properties";

    // 私有构造方法，创建单例
    private PropertiesFileUtil() {
    }

    public static synchronized PropertiesConfiguration getInstance() {
        return getInstance(NAME);
    }

    public static synchronized PropertiesConfiguration getInstance(String name) {
        PropertiesConfiguration propertiesConfiguration = configMap.get(name);
        if (propertiesConfiguration == null) {
            Configurations configs = new Configurations();
            try {
                propertiesConfiguration = configs.properties(name);
            } catch (ConfigurationException e) {
                logger.error("can not load properties file,name : " + name);
            }
            configMap.put(name, propertiesConfiguration);
        }
        return propertiesConfiguration;
    }
}
