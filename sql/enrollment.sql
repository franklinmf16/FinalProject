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

 Date: 25/09/2018 23:16:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for enrollment
-- ----------------------------
DROP TABLE IF EXISTS `enrollment`;
CREATE TABLE `enrollment` (
  `enrollment_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `course_teacher_id` int(11) NOT NULL,
  `done` tinyint(1) DEFAULT '0',
  `enroll_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`enrollment_id`),
  KEY `fk_enr_student_id` (`student_id`),
  KEY `fk_enr_course_teacher_id` (`course_teacher_id`),
  CONSTRAINT `fk_enr_course_teacher_id` FOREIGN KEY (`course_teacher_id`) REFERENCES `course_teacher` (`id`),
  CONSTRAINT `fk_enr_student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of enrollment
-- ----------------------------
BEGIN;
INSERT INTO `enrollment` VALUES (1, 2, 1, 0, NULL, NULL, NULL);
INSERT INTO `enrollment` VALUES (2, 3, 1, 0, NULL, NULL, NULL);
INSERT INTO `enrollment` VALUES (4, 4, 3, 0, NULL, NULL, NULL);
INSERT INTO `enrollment` VALUES (5, 5, 2, 0, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
