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

 Date: 25/09/2018 23:16:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `password` varchar(300) COLLATE utf8_bin NOT NULL,
  `full_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `enroll_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `last_edit_date` datetime DEFAULT NULL,
  `question` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `answer` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (2, '1@1.com', '', 'hi', '123', NULL, NULL, '2018-09-24 23:50:31', NULL, NULL);
INSERT INTO `student` VALUES (3, '3', '', '3', '3', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (4, '4', '', '4', '4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (5, '5', '', '5', '5', NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
