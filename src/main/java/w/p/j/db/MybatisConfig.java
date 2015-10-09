/*
 * Copyright (c) 2015 - 10 - 3  11 : 13 :$second.second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ClassUtils;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;
import w.p.j.util.MyMapper;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by WPJ587 on 2015/9/28.
 */
@Configuration
@EnableAutoConfiguration (exclude={DataSourceAutoConfiguration.class})
public class MybatisConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private DataSource dataSource;
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private DruidDataSourceEntity druidDataSourceEntity;

    @Bean
    public DataSource dataSource() {
        logger.info("------------druidDataSourceEntity------" + druidDataSourceEntity);
        //加载配置文件属性

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(druidDataSourceEntity.getDriverClassName());
        dataSource.setUsername(druidDataSourceEntity.getUsername());
        dataSource.setPassword(druidDataSourceEntity.getPassword());
        dataSource.setUrl(druidDataSourceEntity.getUrl());
        dataSource.setMaxActive(druidDataSourceEntity.getMaxActive());
        dataSource.setMinIdle(druidDataSourceEntity.getMinIdle());
        dataSource.setValidationQuery(druidDataSourceEntity.getValidationQuery());
        dataSource.setTestOnBorrow(druidDataSourceEntity.isTestOnBorrow());
        dataSource.setTestOnReturn(druidDataSourceEntity.isTestOnReturn());
        dataSource.setTestWhileIdle(druidDataSourceEntity.isTestWhileIdle());
        dataSource.setTimeBetweenEvictionRunsMillis(druidDataSourceEntity.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidDataSourceEntity.getMinEictableIdleTimeMillis());
        dataSource.setPoolPreparedStatements(druidDataSourceEntity.isPoolPreparedStatements());
        dataSource.setMaxOpenPreparedStatements(druidDataSourceEntity.getMaxOpenPreparedStatements());
        try {
            dataSource.setFilters(druidDataSourceEntity.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.dataSource=dataSource;
        return dataSource;
    }


    public Resource[] getResource(String basePackage, String pattern) throws IOException {
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + ClassUtils.convertClassNameToResourcePath(new StandardEnvironment()
                .resolveRequiredPlaceholders(basePackage)) + "/" + pattern;
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
        return resources;
    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        logger.info("------> sqlSessionFactory");
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        System.out.println("----first-->");
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        Interceptor[] interceptors = new Interceptor[1];
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "true");
        pageHelper.setProperties(properties);
        interceptors[0] = pageHelper;
        sqlSessionFactory.setPlugins(interceptors);
        sqlSessionFactory.setFailFast(true);
        sqlSessionFactory.setMapperLocations(getResource("mapper", "**/*.xml"));
        //配置插件

        return sqlSessionFactory.getObject();
    }


//    @Bean
//    public DataSourceTransactionManager transactionManager() {
//        logger.info("> transactionManager");
//        DataSourceTransactionManager dataSourceTransactionManager=new DataSourceTransactionManager();
//
//        return new DataSourceTransactionManager(dataSource());
//    }

    @Bean
    @Scope(value = "prototype")//线程安全
    public SqlSessionTemplate sqlSessionTemplate() {

        try {
            logger.info("SqlSessionTemplate---");
            return new SqlSessionTemplate(sqlSessionFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("---sqlSesson----", "null");
        return null;
    }

    @Bean
    @ConditionalOnMissingBean(name = "transactionManager")
    @ConditionalOnBean(DataSource.class)
    public PlatformTransactionManager transactionManager() {
        logger.info("transactionManager---------------->");
        return new DataSourceTransactionManager(dataSource);
    }

}
