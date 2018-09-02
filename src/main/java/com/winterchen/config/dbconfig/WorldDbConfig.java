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
@Configuration
@MapperScan(basePackages = "com.winterchen.dao.world", annotationClass = WorldDatasource.class, sqlSessionFactoryRef = WorldDbConfig.SQL_SESSION_FACTORY_NAME)

public class WorldDbConfig {
    public static final String SQL_SESSION_FACTORY_NAME = "world";
    public static final String TX_MANAGER = "worldRead";
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    private MybatisProperties properties;

    @Bean(name = "worldRead")
    @ConfigurationProperties(prefix = "spring.datasource.world")
    public DataSource worldRead() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = TX_MANAGER)
    public PlatformTransactionManager txManagerUser() {
        return new DataSourceTransactionManager(worldRead());
    }

    @Bean(name = WorldDbConfig.SQL_SESSION_FACTORY_NAME)
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(worldRead());
        sqlSessionFactoryBean.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        sqlSessionFactoryBean.setMapperLocations(this.properties.getMapperLocations());
        return sqlSessionFactoryBean.getObject();
    }
}
