/**
 * 公司：DLUT
 * 文件名：RootConfig
 * 作者：haibing
 * 时间：2020/1/6 15:19
 * 描述：
 */

package com.hundsun.xone.ssm.config;

import com.hundsun.xone.ssm.util.AppContext;
import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;

/**
 * 将xml配置文件加载bean方式改换成类加载bean方式
 * 1.将mysql的数据源配置方法该换
 * 2.通过EnvironmentAware可以获取property,但本例使用@Value获取
 */
@Configuration
@PropertySource(value = {"classpath:config/application.properties"})
@ComponentScan(value = {"com.hundsun.xone.ssm"})
public class RootConfig implements EnvironmentAware {

    private static final String prefix = "spring.mysql.dataSource.";

    @Value("${spring.mysql.dataSource.driverClass}")
    private String driverClass = prefix + "driverClass"/*"com.mysql.jdbc.Driver"*/;

    @Value("${spring.mysql.dataSource.url}")
    private String url = prefix + "url" /*"jdbc:mysql://127.0.0.1:3306/ssm_flowerstore?serverTimezone=UTC&characterEncoding=utf-8&useSSL=false"*/;

    @Value("${spring.mysql.dataSource.username}")
    private String username = prefix + "username"/*"root"*/;

    @Value("${spring.mysql.dataSource.password}")
    private String password = prefix + "password"/*"root"*/;

//    @Value("${spring.mysql.dataSource.defaultReadOnly}")
    private boolean defaultReadOnly = true;

    private static String typeAliasesPackage = "com.hundsun.xone.ssm.entity";

    private static String mapperLocations  = "classpath:mapper/UserMapper.xml";

    private static String basePackage = "com.hundsun.xone.ssm.dao";

    private static boolean enforceReadOnly = true;

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Primary
    @Bean("readOnlyDataSource")
    public BasicDataSource readOnlyDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        this.initDataSource(dataSource);
        return dataSource;
    }

    private void initDataSource(BasicDataSource dataSource) {
       dataSource.setDriverClassName(this.environment.getProperty(driverClass));
       dataSource.setUrl(this.environment.getProperty(url));
       dataSource.setUsername(this.environment.getProperty(username));
       dataSource.setPassword(this.environment.getProperty(password));
       dataSource.setDefaultReadOnly(this.defaultReadOnly);
    }

    @Bean("readOnlySqlSessionFactory")
    public SqlSessionFactoryBean readOnlySqlSessionFactory(@Qualifier("readOnlyDataSource") BasicDataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource locations = resolver.getResource(mapperLocations);
        sqlSessionFactory.setMapperLocations(locations);

        return sqlSessionFactory;
    }

    @Bean("readOnlyMapperScannerConfigurer")
    public MapperScannerConfigurer readOnlyMapperScannerConfigurer(@Qualifier("readOnlySqlSessionFactory") SqlSessionFactoryBean  sqlSessionFactory){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("readOnlySqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(basePackage);

        return mapperScannerConfigurer;
    }

    @Bean("readOnlyDataSourceTransactionManager")
    public DataSourceTransactionManager readOnlyDataSourceTransactionManager(@Qualifier("readOnlyDataSource") BasicDataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setEnforceReadOnly(enforceReadOnly);

        return transactionManager;
    }
}
