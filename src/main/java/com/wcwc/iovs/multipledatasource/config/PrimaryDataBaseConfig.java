package com.wcwc.iovs.multipledatasource.config;

import com.wcwc.iovs.multipledatasource.module.dao.entity.PrimaryProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @description: 主数据源配置
 * @author: lcm
 * @create: 2019-04-25 11:31
 **/
@Configuration
@MapperScan(basePackages = "com.wcwc.iovs.multipledatasource.module.dao.primary", sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryDataBaseConfig {

    @Autowired
    PrimaryProperties primaryProperties;

    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource(){
        HikariDataSource hikariDataSource=new HikariDataSource();
        hikariDataSource.setJdbcUrl(primaryProperties.getUrl());
        hikariDataSource.setDriverClassName(primaryProperties.getDriverClassName());
        hikariDataSource.setUsername(primaryProperties.getUsername());
        hikariDataSource.setPassword(primaryProperties.getPassword());
        hikariDataSource.setIdleTimeout(Long.parseLong(primaryProperties.getHikari().get("idleTimeout")));
        hikariDataSource.setMaximumPoolSize(Integer.parseInt(primaryProperties.getHikari().get("maximumPoolSize")));
        return hikariDataSource;
    }

    /**
     * 创建该数据源的事务管理
     * @return
     * @throws SQLException
     */
    @Primary
    @Bean(name = "primaryTransactionManager")
    public DataSourceTransactionManager primaryTransactionManager(){
        return new DataSourceTransactionManager(primaryDataSource());
    }


    /**
     * 创建Mybatis的连接会话工厂实例
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Primary
    @Bean(name = "primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 设置数据源bean
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                // 设置mapper文件路径
                .getResources(primaryProperties.getMapper()));

        return sessionFactory.getObject();
    }

}
