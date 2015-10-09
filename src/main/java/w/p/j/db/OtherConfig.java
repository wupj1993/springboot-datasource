/*
 * Copyright (c) 2015 - 10 - 4  9 : 3 :$second.second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;
import w.p.j.util.MyMapper;

import javax.annotation.Resource;

/**
 * Created by WPJ587 on 2015/10/4.
 */
@Configuration
public class OtherConfig {
    /**
     * 这个是为了使用通用mapper而独立配置的tk.mybatis.spring.mapper.MapperScannerConfigurer
     * 不能写在MybatisConfig否则胡出现druidDataSourceEntity为空的情况
     * 我也不知道为什么。
     * @return
     */

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("w.p.j.dao");
        mapperScannerConfigurer.setMarkerInterface(MyMapper.class);
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }
}
