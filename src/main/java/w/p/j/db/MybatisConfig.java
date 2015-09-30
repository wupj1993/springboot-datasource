/*
 * Copyright (c) 2015 - 9 - 28  11 : 44 :$second.second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by WPJ587 on 2015/9/28.
 */
@Configuration
@MapperScan(basePackages = "w.p.j.dao")
public class MybatisConfig {
    private Logger logger= LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private DruidDataSourceEntity druidDataSourceEntity;
    @Bean
    public DataSource dataSource() {
        logger.debug("druidDataSourceEntity"+druidDataSourceEntity);
        //加载配置文件属性
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(druidDataSourceEntity.getDriverClassName());
        ds.setUsername(druidDataSourceEntity.getUsername());
        ds.setPassword(druidDataSourceEntity.getPassword());
        ds.setUrl(druidDataSourceEntity.getUrl());
        ds.setMaxActive(druidDataSourceEntity.getMaxActive());
        ds.setValidationQuery(druidDataSourceEntity.getValidationQuery());
        ds.setTestOnBorrow(druidDataSourceEntity.isTestOnBorrow());
        ds.setTestOnReturn(druidDataSourceEntity.isTestOnReturn());
        ds.setTestWhileIdle(druidDataSourceEntity.isTestWhileIdle());
        ds.setTimeBetweenEvictionRunsMillis(druidDataSourceEntity.getTimeBetweenEvictionRunsMillis());
        ds.setMinEvictableIdleTimeMillis(druidDataSourceEntity.getMinEictableIdleTimeMillis());
        ds.setPoolPreparedStatements(druidDataSourceEntity.isPoolPreparedStatements());
        ds.setMaxOpenPreparedStatements(druidDataSourceEntity.getMaxOpenPreparedStatements());
        try {
            ds.setFilters(druidDataSourceEntity.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
    public Resource[] getResource( String basePackage, String pattern ) throws IOException {
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + ClassUtils.convertClassNameToResourcePath(new StandardEnvironment()
                .resolveRequiredPlaceholders(basePackage)) + "/" + pattern;
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
        return resources;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        logger.debug("--> sqlSessionFactory");
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactory.setFailFast(true);
        sqlSessionFactory.setMapperLocations(getResource("mapper", "**/*.xml"));
        return sqlSessionFactory.getObject();
    }


    @Bean
    public DataSourceTransactionManager transactionManager() {
        logger.debug("> transactionManager");
        return new DataSourceTransactionManager(dataSource());
    }


    @PostConstruct
    public void postConstruct() {
        logger.info("jdbc.settings={}", druidDataSourceEntity);
    }
}
