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

 Date: 25/09/2018 23:15:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course_teacher
-- ----------------------------
DROP TABLE IF EXISTS `course_teacher`;
CREATE TABLE `course_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `create_date` datetime DEFAULT NULL,
  `last_edit_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_teacher_index` (`course_id`,`teacher_id`),
  KEY `fk_teacher_id` (`teacher_id`),
  CONSTRAINT `fk_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `fk_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of course_teacher
-- ----------------------------
BEGIN;
INSERT INTO `course_teacher` VALUES (1, 1, 1, 1, NULL, NULL);
INSERT INTO `course_teacher` VALUES (2, 2, 1, 1, NULL, NULL);
INSERT INTO `course_teacher` VALUES (3, 1, 2, 1, NULL, NULL);
INSERT INTO `course_teacher` VALUES (4, 2, 2, 1, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
