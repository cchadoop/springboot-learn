/*
 Navicat Premium Data Transfer

 Source Server         : mydb
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 10/07/2019 11:33:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_city
-- ----------------------------
DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_city
-- ----------------------------
BEGIN;
INSERT INTO `t_city` VALUES (1, '深圳', '高科技研发3');
INSERT INTO `t_city` VALUES (2, '广州', '羊城');
INSERT INTO `t_city` VALUES (3, '抚州', '才子之乡');
INSERT INTO `t_city` VALUES (4, '休斯顿', '火箭');
INSERT INTO `t_city` VALUES (6, '洛杉矶', '湖人');
INSERT INTO `t_city` VALUES (9, 'a', 'b');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
