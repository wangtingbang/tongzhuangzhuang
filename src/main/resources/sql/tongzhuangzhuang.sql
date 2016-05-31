/*
Navicat MySQL Data Transfer

Source Host           : localhost:3306
Source Database       : tongzhuangzhuang

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2016-05-31 18:15:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cust_customer_base
-- ----------------------------
DROP TABLE IF EXISTS `cust_customer_base`;
CREATE TABLE `cust_customer_base` (
  `id` varchar(32) NOT NULL,
  `login_name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `user_name` varchar(32) NOT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `delete_flag` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for prd_category
-- ----------------------------
DROP TABLE IF EXISTS `prd_category`;
CREATE TABLE `prd_category` (
  `id` varchar(32) NOT NULL,
  `category_name` varchar(64) DEFAULT NULL,
  `category_type` tinyint(3) DEFAULT NULL,
  `category_rate` float DEFAULT NULL,
  `delete_flag` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
