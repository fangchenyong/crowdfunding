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

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 240 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '经理');
INSERT INTO `t_role` VALUES (237, '部长');
INSERT INTO `t_role` VALUES (238, '经理操作者');
INSERT INTO `t_role` VALUES (239, '部长操作者');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, NULL, '系统权限菜单', NULL, 'glyphicon\r\nglyphicon-th-list');
INSERT INTO `t_menu` VALUES (2, 1, '控 制 面 板', 'main.htm', 'glyphicon glyphicon glyphicon-tasks');
INSERT INTO `t_menu` VALUES (3, 1, '权限管理', NULL, 'glyphicon glyphicon\r\nglyphicon-tasks');
INSERT INTO `t_menu` VALUES (4, 3, ' 用 户 维 护 ', 'user/index.htm', 'glyphicon\r\nglyphicon-user');
INSERT INTO `t_menu` VALUES (5, 3, ' 角 色 维 护 ', 'role/index.htm', 'glyphicon\r\nglyphicon-king');
INSERT INTO `t_menu` VALUES (6, 3, ' 菜 单 维 护 ', 'permission/index.htm', 'glyphicon\r\nglyphicon-lock');
INSERT INTO `t_menu` VALUES (7, 1, ' 业 务 审 核 ', NULL, 'glyphicon\r\nglyphicon-ok');
INSERT INTO `t_menu` VALUES (8, 7, '实名认证审核', 'auth_cert/index.htm', 'glyphicon\r\nglyphicon-check');
INSERT INTO `t_menu` VALUES (9, 7, ' 广 告 审 核 ', 'auth_adv/index.htm', 'glyphicon\r\nglyphicon-check');
INSERT INTO `t_menu` VALUES (10, 7, ' 项 目 审 核 ', 'auth_project/index.htm', 'glyphicon\r\nglyphicon-check');
INSERT INTO `t_menu` VALUES (11, 1, ' 业 务 管 理 ', NULL, 'glyphicon\r\nglyphicon-th-large');
INSERT INTO `t_menu` VALUES (12, 11, ' 资 质 维 护 ', 'cert/index.htm', 'glyphicon\r\nglyphicon-picture');
INSERT INTO `t_menu` VALUES (13, 11, ' 分 类 管 理 ', 'certtype/index.htm', 'glyphicon\r\nglyphicon-equalizer');
INSERT INTO `t_menu` VALUES (14, 11, ' 流 程 管 理 ', 'process/index.htm', 'glyphicon\r\nglyphicon-random');
INSERT INTO `t_menu` VALUES (15, 11, ' 广 告 管 理 ', 'advert/index.htm', 'glyphicon\r\nglyphicon-hdd');
INSERT INTO `t_menu` VALUES (16, 11, ' 消 息 模 板 ', 'message/index.htm', 'glyphicon\r\nglyphicon-comment');
INSERT INTO `t_menu` VALUES (17, 11, ' 项 目 分 类 ', 'projectType/index.htm', 'glyphicon\r\nglyphicon-list');
INSERT INTO `t_menu` VALUES (18, 11, ' 项 目 标 签 ', 'tag/index.htm', 'glyphicon\r\nglyphicon-tags');
INSERT INTO `t_menu` VALUES (19, 1, ' 参 数 管 理 ', 'param/index.htm', 'glyphicon\r\nglyphicon-list-alt');

-- ----------------------------
-- Table structure for inner_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `inner_admin_role`;
CREATE TABLE `inner_admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inner_admin_role
-- ----------------------------
INSERT INTO `inner_admin_role` VALUES (5, 2, 1);
INSERT INTO `inner_admin_role` VALUES (6, 2, 238);
INSERT INTO `inner_admin_role` VALUES (7, 3, 237);
INSERT INTO `inner_admin_role` VALUES (8, 3, 239);

-- ----------------------------
-- Table structure for inner_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `inner_role_auth`;
CREATE TABLE `inner_role_auth`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `auth_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inner_role_auth
-- ----------------------------
INSERT INTO `inner_role_auth` VALUES (1, 1, 2);
INSERT INTO `inner_role_auth` VALUES (8, 238, 1);
INSERT INTO `inner_role_auth` VALUES (9, 238, 8);
INSERT INTO `inner_role_auth` VALUES (12, 239, 4);
INSERT INTO `inner_role_auth` VALUES (13, 239, 5);

-- ----------------------------
-- Table structure for t_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_auth`;
CREATE TABLE `t_auth`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_auth
-- ----------------------------
INSERT INTO `t_auth` VALUES (1, '', '用户模块', NULL);
INSERT INTO `t_auth` VALUES (2, 'user:delete', '删除', 1);
INSERT INTO `t_auth` VALUES (3, 'user:get', '查询', 1);
INSERT INTO `t_auth` VALUES (4, '', '角色模块', NULL);
INSERT INTO `t_auth` VALUES (5, 'role:delete', '删除', 4);
INSERT INTO `t_auth` VALUES (6, 'role:get', '查询', 4);
INSERT INTO `t_auth` VALUES (7, 'role:add', '新增', 4);
INSERT INTO `t_auth` VALUES (8, 'user:save', '保存', 1);


SET FOREIGN_KEY_CHECKS = 1;
