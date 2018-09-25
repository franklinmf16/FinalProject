/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : piano_school

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 25/09/2018 23:16:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `full_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `last_edit_date` datetime DEFAULT NULL,
  `question` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `answer` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
INSERT INTO `teacher` VALUES (1, '1@1.com', '202CB962AC59075B964B07152D234B70', NULL, NULL, '2018-09-24 15:20:20', '2018-09-24 15:20:20', NULL, NULL);
INSERT INTO `teacher` VALUES (2, '1@2.com', '202CB962AC59075B964B07152D234B70', NULL, NULL, '2018-09-25 00:26:11', '2018-09-25 00:26:11', NULL, NULL);
INSERT INTO `teacher` VALUES (3, '1@2.com', '202CB962AC59075B964B07152D234B70', NULL, NULL, '2018-09-25 00:28:42', '2018-09-25 00:28:42', NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
