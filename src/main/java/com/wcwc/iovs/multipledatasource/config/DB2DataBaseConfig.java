package com.wcwc.iovs.multipledatasource.config;

import com.wcwc.iovs.multipledatasource.module.dao.entity.DB2Properties;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @description:
 * @author: lcm
 * @create: 2019-04-25 15:00
 **/
@Configuration
@MapperScan(basePackages = "com.wcwc.iovs.multipledatasource.module.dao.db2", sqlSessionFactoryRef = "db2SqlSessionFactory")
public class DB2DataBaseConfig {

    @Autowired
    DB2Properties db2Properties;

    @Bean(name = "db2DataSource")
    public DataSource db2DataSource(){
        HikariDataSource hikariDataSource=new HikariDataSource();
        hikariDataSource.setJdbcUrl(db2Properties.getUrl());
        hikariDataSource.setDriverClassName(db2Properties.getDriverClassName());
        hikariDataSource.setUsername(db2Properties.getUsername());
        hikariDataSource.setPassword(db2Properties.getPassword());
        hikariDataSource.setIdleTimeout(Long.parseLong(db2Properties.getHikari().get("idleTimeout")));
        hikariDataSource.setMaximumPoolSize(Integer.parseInt(db2Properties.getHikari().get("maximumPoolSize")));
        return hikariDataSource;
    }

    @Bean(name = "db2TransactionManager")
    public DataSourceTransactionManager db2TransactionManager(){
        return new DataSourceTransactionManager(db2DataSource());
    }

    @Bean(name = "db2SqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 设置数据源bean
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                // 设置mapper文件路径
                .getResources(db2Properties.getMapper()));

        return sessionFactory.getObject();
    }
}
