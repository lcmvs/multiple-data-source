package com.wcwc.iovs.multipledatasource;

import com.wcwc.iovs.multipledatasource.module.dao.db2.DB2Dao;
import com.wcwc.iovs.multipledatasource.module.dao.entity.PrimaryProperties;
import com.wcwc.iovs.multipledatasource.module.dao.primary.PrimaryDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultipleDataSourceApplicationTests {

    @Autowired
    PrimaryProperties primaryProperties;

    @Autowired
    PrimaryDao primaryDao;

    @Autowired
    DB2Dao db2Dao;

    @Test
    public void contextLoads() {
        System.out.println(primaryProperties.getHikari());
        System.out.println(primaryDao.select());
        System.out.println(db2Dao.select());
    }

}
