package com.wcwc.iovs.multipledatasource.module.dao.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: db2属性实体类
 * @author: lcm
 * @create: 2019-04-25 11:58
 **/
@Data
@Component
@ConfigurationProperties(prefix="db2")
public class DB2Properties {

    private String mapper;
    private String url;
    private String driverClassName;
    private String username;
    private String password;
    private Map<String,String> hikari;

}
