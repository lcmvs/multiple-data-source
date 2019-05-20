package com.wcwc.iovs.multipledatasource.module.dao.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: db1属性实体类
 * @author: lcm
 * @create: 2019-04-25 11:57
 **/
@Data
@Component
@ConfigurationProperties(prefix="db1")
public class PrimaryProperties {

    private String mapper;
    private String url;
    private String driverClassName;
    private String username;
    private String password;
    private Map<String,String> hikari;

}
