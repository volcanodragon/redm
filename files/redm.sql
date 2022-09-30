BEGIN;

CREATE DATABASE redm;

USE redm;

/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : 127.0.0.1:3306
 Source Schema         : redm

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 30/09/2022 17:02:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL DEFAULT 0 AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` int NOT NULL COMMENT '创建人id',
  `creator` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人姓名',
  `deleted` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标识 0-存在 1-删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '{bcrypt}$2a$12$v62ybcBsOrs0QzAxauT.z.v/Wfc8cjdk6bBFzz72jz.H5hK1xeVRy', '', '2022-09-30 16:23:25', 0, '', 0);

SET FOREIGN_KEY_CHECKS = 1;

COMMIT;
