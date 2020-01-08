/**
 * 公司：DLUT
 * 文件名：RootConfig
 * 作者：haibing
 * 时间：2020/1/6 15:19
 * 描述：
 */

package com.hundsun.xone.ssm.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 替代 spring-mybatis.xml文件
 * 将xml配置文件加载bean方式改换成类加载bean方式
 * 1.将mysql的数据源配置方法该换
 * 2.通过PropertySource 将配置文件加载到Context中， 再通过EnvironmentAware可以从contxet获取property,本例使用@Value获取
 */
@Configuration
@PropertySource(value = {"classpath:config/application.properties"})
@ComponentScan(value = {"com.hundsun.xone.ssm"})
public class RootConfig implements EnvironmentAware {

    private static final String prefix = "spring.mysql.dataSource.";

    @Value("${spring.mysql.dataSource.driverClass}")
    private String driverClass;

    @Value("${spring.mysql.dataSource.url}")
    private String url;

    @Value("${spring.mysql.dataSource.username}")
    private String username;

    @Value("${spring.mysql.dataSource.password}")
    private String password;

    private static boolean enforceReadOnly = true;

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 通过jdbcTempalte执行数据库操作
     * @param dataSource
     * @return
     */
    @Bean("readOnlyJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("readOnlyDataSource") BasicDataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean("writeAndReadJdbcTemplate")
    public JdbcTemplate writeAndReadJdbcTemplate(@Qualifier("writeAndReadDataSource") BasicDataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Primary
    @Bean("readOnlyDataSource")
    public BasicDataSource readOnlyDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        // 允许只读
        dataSource.setDefaultReadOnly(true);
        this.initDataSource(dataSource);
        return dataSource;
    }

    @Bean("writeAndReadDataSource")
    @Primary
    public BasicDataSource writeAndReadDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        // 允许读写
        dataSource.setDefaultReadOnly(false);
        this.initDataSource(dataSource);
        return dataSource;
    }

    /**
     * 初始化dataSource
     * @param dataSource
     */
    private void initDataSource(BasicDataSource dataSource) {
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
    }

    /**
     * 事务管理, 强制限制只读操作
     * @param dataSource
     * @return
     */
    @Bean("readOnlyDataSourceTransactionManager")
    public DataSourceTransactionManager readOnlyDataSourceTransactionManager(@Qualifier("readOnlyDataSource") BasicDataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setEnforceReadOnly(enforceReadOnly);

        return transactionManager;
    }
}
