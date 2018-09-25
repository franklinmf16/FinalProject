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

 Date: 25/09/2018 23:16:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for teacher_feedback
-- ----------------------------
DROP TABLE IF EXISTS `teacher_feedback`;
CREATE TABLE `teacher_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enrollment_id` int(11) DEFAULT NULL,
  `comments` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `last_edit_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_enrollment_id` (`enrollment_id`),
  CONSTRAINT `fk_enrollment_id` FOREIGN KEY (`enrollment_id`) REFERENCES `enrollment` (`enrollment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of teacher_feedback
-- ----------------------------
BEGIN;
INSERT INTO `teacher_feedback` VALUES (1, 1, 'nice', NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
