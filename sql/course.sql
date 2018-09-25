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

 Date: 25/09/2018 23:15:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL,
  `course_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `course_description` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `course_material` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `last_edit_date` datetime DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of course
-- ----------------------------
BEGIN;
INSERT INTO `course` VALUES (1, 'beginer', 'piano course for beginer', 'binger book', NULL, NULL);
INSERT INTO `course` VALUES (2, 'mid', 'mid user', 'mid book', NULL, NULL);
INSERT INTO `course` VALUES (3, 'adv', 'adv user', 'adv book', NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
