package com.fangchy;

import com.fangchy.entity.Admin;
import com.fangchy.mapper.AdminMapper;
import com.fangchy.service.api.IAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName: crowdTest
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/7/21 16:06
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private IAdminService adminService;

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testInsert(){
        Admin admin = new Admin(null, "joey", "123456", "joey", "1@qq.com", "");
        int i = adminMapper.insert(admin);
        System.out.println("插入行数："+i);
    }

    @Test
    public void testLog(){
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        logger.debug("Debug级别日志");
        logger.debug("Debug级别日志");
        logger.debug("Debug级别日志");
        logger.info("Info级别日志");
        logger.info("Info级别日志");
        logger.info("Info级别日志");
        logger.error("Error级别日志");
        logger.error("Error级别日志");
        logger.error("Error级别日志");

    }

    @Test
    public void testTx(){
        Admin admin = new Admin(null, "joey123456", "123456", "joey123456", "1@qq.com", "");
        adminService.saveAdmin(admin);
    }
}
