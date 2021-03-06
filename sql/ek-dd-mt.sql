/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : ek-dd-mt

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-09-06 17:51:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dd_material_base_info
-- ----------------------------
DROP TABLE IF EXISTS `dd_material_base_info`;
CREATE TABLE `dd_material_base_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `MT_TYPE_CODE` varchar(64) DEFAULT NULL,
  `MT_TYPE_NAME` varchar(64) NOT NULL,
  `MT_TYPE_MS_UNITS` varchar(64) NOT NULL,
  `MT_TYPE_PRICE_UNITS` double NOT NULL,
  `DD_CORP_ID` varchar(64) NOT NULL,
  `DD_DEPT_ID` int(11) DEFAULT NULL,
  `ATTRIBUTE_1` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_2` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_3` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_4` varchar(255) DEFAULT NULL,
  `CREATE_BY` varchar(255) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dd_material_base_info
-- ----------------------------
INSERT INTO `dd_material_base_info` VALUES ('2', '201909041658051459136491', '沙子', '袋', '8', 'dingbcb401623a369ad835c2f4657eb6378f', null, null, null, null, null, '秦汉卿', '2019-09-04 16:58:05');
INSERT INTO `dd_material_base_info` VALUES ('3', '2019090417044612981631141', '水泥', '袋', '10', 'dingbcb401623a369ad835c2f4657eb6378f', null, null, null, null, null, '秦汉卿', '2019-09-04 17:04:46');
INSERT INTO `dd_material_base_info` VALUES ('4', '201909041705412560742442', '钢筋', '吨', '1500', 'dingbcb401623a369ad835c2f4657eb6378f', null, null, null, null, null, '秦汉卿', '2019-09-04 17:05:41');
INSERT INTO `dd_material_base_info` VALUES ('5', '2019090417140315155659663', '混凝土', '吨', '1000', 'dingbcb401623a369ad835c2f4657eb6378f', null, null, null, null, null, '秦汉卿', '2019-09-04 17:14:04');
INSERT INTO `dd_material_base_info` VALUES ('6', '201909041721475269604164', '沥青', '吨', '2000', 'dingbcb401623a369ad835c2f4657eb6378f', null, null, null, null, null, '秦汉卿', '2019-09-04 17:21:47');
INSERT INTO `dd_material_base_info` VALUES ('7', '201909041725157246021935', '砖头', '块', '0.05', 'dingbcb401623a369ad835c2f4657eb6378f', null, null, null, null, null, '秦汉卿', '2019-09-04 17:25:15');
INSERT INTO `dd_material_base_info` VALUES ('8', '2019090417263315591345246', '空心砖', '块', '0.1', 'dingbcb401623a369ad835c2f4657eb6378f', null, null, null, null, null, '秦汉卿', '2019-09-04 17:26:33');
INSERT INTO `dd_material_base_info` VALUES ('9', '2019090417315416835005457', '石膏', '吨', '500', 'dingbcb401623a369ad835c2f4657eb6378f', null, null, null, null, null, '秦汉卿', '2019-09-04 17:31:54');
INSERT INTO `dd_material_base_info` VALUES ('10', '201909041734254298197668', '石灰', '吨', '800', 'dingbcb401623a369ad835c2f4657eb6378f', null, null, null, null, null, '秦汉卿', '2019-09-04 17:34:25');

-- ----------------------------
-- Table structure for dd_material_work_order
-- ----------------------------
DROP TABLE IF EXISTS `dd_material_work_order`;
CREATE TABLE `dd_material_work_order` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MT_WO_CODE` varchar(64) NOT NULL,
  `MT_WO_DESC` varchar(256) NOT NULL,
  `MT_WO_USE_DATE` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `MT_BASE_ID` int(11) NOT NULL,
  `MT_BASE_AMOUNT` double NOT NULL,
  `MT_WO_TOTAL_PRICE` double(10,2) NOT NULL,
  `DD_DEPT_ID` bigint(11) DEFAULT NULL,
  `DD_CORP_ID` varchar(64) DEFAULT NULL,
  `DD_USER_ID` varchar(255) DEFAULT NULL,
  `DD_PROCESS_INSTANCE_ID` varchar(64) DEFAULT NULL,
  `STATUS` varchar(2) NOT NULL DEFAULT '0',
  `ATTRIBUTE_1` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_2` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_3` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_4` varchar(255) DEFAULT NULL,
  `CREATE_BY` varchar(255) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dd_material_work_order
-- ----------------------------
INSERT INTO `dd_material_work_order` VALUES ('29', '201909061558151271103301', '测试', '2019-09-06 15:58:57', '2', '50', '400.00', '-1', 'dingbcb401623a369ad835c2f4657eb6378f', '011765146230869692', 'efe32e4e-df0a-4099-9a2b-006f127ef737', '10', null, null, null, null, '秦汉卿', '2019-09-06 15:58:57');
INSERT INTO `dd_material_work_order` VALUES ('30', '2019090616013816416759342', '钢筋测试2', '2019-10-09 00:00:00', '4', '3', '4500.00', '-1', 'dingbcb401623a369ad835c2f4657eb6378f', '011765146230869692', 'eddd603c-93bf-4545-9648-375a3c5ce88f', '00', null, null, null, null, '秦汉卿', '2019-09-06 16:01:38');
INSERT INTO `dd_material_work_order` VALUES ('31', '2019090617444811605780031', '沙子测试2', '2019-09-06 17:45:22', '2', '100', '800.00', '-1', 'dingbcb401623a369ad835c2f4657eb6378f', '011765146230869692', '746a81c9-86c6-451f-8bd0-9724ea2e62ff', '10', null, null, null, null, '秦汉卿', '2019-09-06 17:45:22');

-- ----------------------------
-- Table structure for ek_user
-- ----------------------------
DROP TABLE IF EXISTS `ek_user`;
CREATE TABLE `ek_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) NOT NULL,
  `password` varchar(64) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `valid_flag` varchar(1) NOT NULL DEFAULT '0',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ek_user
-- ----------------------------
INSERT INTO `ek_user` VALUES ('1', 'admin', '$2a$05$jocSspsqrXyLwPsf6awLtuBSR6zdyOpWR.mGdPcxiBbqvuSD9Eqse', 'admin', null, '0', '2019-09-04 11:21:00', null);
