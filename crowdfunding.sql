/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : crowdfunding

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 21/07/2020 17:26:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_acct` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_pswd` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_acct`(`login_acct`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 252 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'tom', '$2a$10$XtrmAW1MRrzhJFDT75rBpu1HVVlffHjcmcmN161o7KhU1XUyrhcOK', '汤姆', '1822014927@qq.com', NULL);
INSERT INTO `t_admin` VALUES (2, 'adminOperator', '$2a$10$XtrmAW1MRrzhJFDT75rBpu1HVVlffHjcmcmN161o7KhU1XUyrhcOK', 'AAOO', '1822014927@qq.com', NULL);
INSERT INTO `t_admin` VALUES (3, 'roleOperator', '$2a$10$XtrmAW1MRrzhJFDT75rBpu1HVVlffHjcmcmN161o7KhU1XUyrhcOK', 'RROO', '18220214927@qq.com', NULL);
INSERT INTO `t_admin` VALUES (248, 'admin01', '2222', 'admin01', 'aaa', NULL);
INSERT INTO `t_admin` VALUES (249, 'admin02', '2222', 'admin02', 'bbb', NULL);
INSERT INTO `t_admin` VALUES (250, 'admin03', '3333', 'admin03', 'www', NULL);
INSERT INTO `t_admin` VALUES (251, 'joey', '123456', 'joey', '1@qq.com', '');

SET FOREIGN_KEY_CHECKS = 1;
