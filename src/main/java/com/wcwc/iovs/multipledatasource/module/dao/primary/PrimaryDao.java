package com.wcwc.iovs.multipledatasource.module.dao.primary;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description:
 * @author: lcm
 * @create: 2019-04-25 11:36
 **/
@Component
public interface PrimaryDao {

    Map select();

}
