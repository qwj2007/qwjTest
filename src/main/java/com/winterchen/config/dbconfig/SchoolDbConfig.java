package com.winterchen.config.dbconfig;

import com.winterchen.mybatis.MybatisProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.lang.invoke.MethodHandles;

/**
 * 作者：齐文杰
 * 时间：2018/7/29
 */
@Configuration
@MapperScan(basePackages = "com.winterchen.dao.school", annotationClass = SchoolDataSource.class, sqlSessionFactoryRef = SchoolDbConfig.SQL_SESSION_FACTORY_NAME)

public class SchoolDbConfig {
    public static final String SQL_SESSION_FACTORY_NAME = "school";
    public static final String TX_MANAGER = "school";
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    private MybatisProperties properties;

    @Bean(name = "schoolRead")
    @ConfigurationProperties(prefix = "spring.datasource.school")
    public DataSource schoolRead() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = TX_MANAGER)
    public PlatformTransactionManager txManagerUser() {
        return new DataSourceTransactionManager(schoolRead());
    }

    @Bean(name = SchoolDbConfig.SQL_SESSION_FACTORY_NAME)
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(schoolRead());
        sqlSessionFactoryBean.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        sqlSessionFactoryBean.setMapperLocations(this.properties.getMapperLocations());
        return sqlSessionFactoryBean.getObject();
    }
}
