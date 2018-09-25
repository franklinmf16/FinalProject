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

 Date: 25/09/2018 23:16:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lesson
-- ----------------------------
DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson` (
  `lesson_id` int(11) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `last_edit_date` datetime DEFAULT NULL,
  `lesson_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lesson_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`lesson_id`),
  KEY `fk_lesson_course_id` (`course_id`),
  CONSTRAINT `fk_lesson_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of lesson
-- ----------------------------
BEGIN;
INSERT INTO `lesson` VALUES (1, 1, NULL, NULL, 'pop', '001');
INSERT INTO `lesson` VALUES (2, 1, NULL, NULL, 'pop', '002');
INSERT INTO `lesson` VALUES (3, 2, NULL, NULL, 'pop', '001');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
