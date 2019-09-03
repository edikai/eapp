/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : ek-dd-mt

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-09-03 17:53:57
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dd_material_base_info
-- ----------------------------

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
INSERT INTO `ek_user` VALUES ('1', 'admin', '$2a$14$QMlyAe5rtD8iULUjyAthReGrsNgxa4NsC5FVOe.5e9ALDSNPNza/S', 'admin', null, '0', '2019-09-03 17:20:23', null);
