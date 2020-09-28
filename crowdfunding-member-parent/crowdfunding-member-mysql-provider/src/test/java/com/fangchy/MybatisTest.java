package com.fangchy;

import com.fangchy.entity.po.MemberPO;
import com.fangchy.entity.vo.DetailProjectVO;
import com.fangchy.entity.vo.DetailReturnVO;
import com.fangchy.entity.vo.PortalProjectVO;
import com.fangchy.entity.vo.PortalTypeVO;
import com.fangchy.mapper.MemberPOMapper;
import com.fangchy.mapper.ProjectPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: MybatisTest
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/8/24 21:42
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberPOMapper memberPOMapper;

    @Autowired
    ProjectPOMapper projectPOMapper;

    private Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        logger.debug("创建连接：" + connection.toString());
        //connection.close();
    }

    @Test
    public void testMapper() {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String source = "123123";

        String encode = passwordEncoder.encode(source);

        MemberPO memberPO = new MemberPO(null, "jack", encode, "杰克", "jack@qq.com", 1, 1, "杰克", "123123", 2);

        memberPOMapper.insert(memberPO);
    }

    @Test
    public void testLoadTypeData() {

        List<PortalTypeVO> typeVOList = projectPOMapper.selectPortalTypeVOList();

        for (PortalTypeVO portalTypeVO : typeVOList) {
            String name = portalTypeVO.getName();
            String remark = portalTypeVO.getRemark();
            logger.info("name=" + name + " remark=" + remark);

            List<PortalProjectVO> projectVOList = portalTypeVO.getPortalProjectVOList();
            for (PortalProjectVO portalProjectVO : projectVOList) {

                if (portalProjectVO == null) {
                    continue;
                }

                logger.info(portalProjectVO.toString());
            }

        }
    }

    @Test
    public void testLoadDetailProjectVO() {

        Integer projectId = 6;

        DetailProjectVO detailProjectVO = projectPOMapper.selectDetailProjectVO(projectId);

        logger.info(detailProjectVO.getProjectId() + "");
        logger.info(detailProjectVO.getProjectName());
        logger.info(detailProjectVO.getProjectDesc());
        logger.info(detailProjectVO.getFollowerCount() + "");
        logger.info(detailProjectVO.getStatus() + "");
        logger.info(detailProjectVO.getMoney() + "");
        logger.info(detailProjectVO.getSupportMoney() + "");
        logger.info(detailProjectVO.getPercentage() + "");
        logger.info(detailProjectVO.getDeployDate() + "");
        logger.info(detailProjectVO.getSupporterCount() + "");
        logger.info(detailProjectVO.getHeaderPicturePath());

        List<String> detailPicturePathList = detailProjectVO.getDetailPicturePathList();
        for (String path : detailPicturePathList) {
            logger.info("detail path=" + path);
        }

        List<DetailReturnVO> detailReturnVOList = detailProjectVO.getDetailReturnVOList();
        for (DetailReturnVO detailReturnVO : detailReturnVOList) {
            logger.info(detailReturnVO.getReturnId() + "");
            logger.info(detailReturnVO.getSupportMoney() + "");
            logger.info(detailReturnVO.getSignalPurchase() + "");
            logger.info(detailReturnVO.getPurchase() + "");
            logger.info(detailReturnVO.getSupproterCount() + "");
            logger.info(detailReturnVO.getFreight() + "");
            logger.info(detailReturnVO.getReturnDate() + "");
            logger.info(detailReturnVO.getContent() + "");
            logger.info(detailReturnVO.getFreight() + "");
        }
    }
}
