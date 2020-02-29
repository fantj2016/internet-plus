/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 50639
 Source Host           : fantj.top:3306
 Source Schema         : internet_plus

 Target Server Type    : MySQL
 Target Server Version : 50639
 File Encoding         : 65001

 Date: 29/02/2020 12:27:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ip_academy
-- ----------------------------
DROP TABLE IF EXISTS `ip_academy`;
CREATE TABLE `ip_academy`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `academy_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_activity
-- ----------------------------
DROP TABLE IF EXISTS `ip_activity`;
CREATE TABLE `ip_activity`  (
  `act_id` int(11) NOT NULL AUTO_INCREMENT,
  `act_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `act_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `act_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `act_create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `act_update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`act_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_admin
-- ----------------------------
DROP TABLE IF EXISTS `ip_admin`;
CREATE TABLE `ip_admin`  (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin_login_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin_login_pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin_level` int(1) NULL DEFAULT NULL COMMENT '0是最高权限，1...',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_change_passwd
-- ----------------------------
DROP TABLE IF EXISTS `ip_change_passwd`;
CREATE TABLE `ip_change_passwd`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_index`(`user_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_competition
-- ----------------------------
DROP TABLE IF EXISTS `ip_competition`;
CREATE TABLE `ip_competition`  (
  `cpt_id` int(11) NOT NULL AUTO_INCREMENT,
  `cpt_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '竞赛类型',
  `cpt_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cpt_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cpt_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cpt_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `cpt_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cpt_apply_time` datetime(0) NOT NULL COMMENT '申请时间',
  `cpt_start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `cpt_end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `cpt_support_id` int(11) NULL DEFAULT NULL COMMENT '企业支持',
  `cpt_status` int(1) NULL DEFAULT NULL,
  `cpt_create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `cpt_update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`cpt_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_cpt_grade
-- ----------------------------
DROP TABLE IF EXISTS `ip_cpt_grade`;
CREATE TABLE `ip_cpt_grade`  (
  `grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_group_id` int(11) NULL DEFAULT NULL,
  `grade_result` int(11) NOT NULL,
  `grade_create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `grade_update_time` datetime(0) NULL DEFAULT NULL,
  `cpt_id` int(11) NULL DEFAULT NULL COMMENT '竞赛id',
  PRIMARY KEY (`grade_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_cpt_support
-- ----------------------------
DROP TABLE IF EXISTS `ip_cpt_support`;
CREATE TABLE `ip_cpt_support`  (
  `sup_id` int(11) NOT NULL AUTO_INCREMENT,
  `sup_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sup_create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `sup_update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`sup_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_file
-- ----------------------------
DROP TABLE IF EXISTS `ip_file`;
CREATE TABLE `ip_file`  (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `cpt_id` int(11) NULL DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_type` int(11) NULL DEFAULT NULL,
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_group
-- ----------------------------
DROP TABLE IF EXISTS `ip_group`;
CREATE TABLE `ip_group`  (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_header_id` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '队长名字',
  `group_school_id` int(4) NULL DEFAULT NULL COMMENT 'school_id',
  `group_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_type` int(1) NULL DEFAULT NULL COMMENT '0大数据、1移动互联、2信息安全',
  `group_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_status` int(1) NULL DEFAULT NULL COMMENT '0:未激活   1：激活',
  `group_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `group_update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`group_id`) USING BTREE,
  UNIQUE INDEX `unique_group_name`(`group_name`) USING BTREE,
  INDEX `key_index`(`group_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_group_members
-- ----------------------------
DROP TABLE IF EXISTS `ip_group_members`;
CREATE TABLE `ip_group_members`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NULL DEFAULT NULL,
  `group_type` int(11) NULL DEFAULT NULL,
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_identity` int(1) NULL DEFAULT NULL COMMENT '0:队员；1：队长；',
  `user_status` int(1) NULL DEFAULT NULL COMMENT '0：未加入，1：已加入  -1：被邀请未接受',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `group_user`(`user_id`) USING BTREE,
  INDEX `group_groupMember`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_guest
-- ----------------------------
DROP TABLE IF EXISTS `ip_guest`;
CREATE TABLE `ip_guest`  (
  `guest_id` int(11) NOT NULL AUTO_INCREMENT,
  `guest_cpt_id` int(11) NULL DEFAULT NULL COMMENT '比赛的id',
  `guest_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guest_type` int(1) NULL DEFAULT NULL COMMENT '0:专家   1：评委',
  `guest_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像链接',
  `guest_descrip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业描述',
  PRIMARY KEY (`guest_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_image
-- ----------------------------
DROP TABLE IF EXISTS `ip_image`;
CREATE TABLE `ip_image`  (
  `img_id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_id` int(11) NULL DEFAULT NULL,
  `img_type` int(11) NULL DEFAULT NULL,
  `img_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`img_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_news
-- ----------------------------
DROP TABLE IF EXISTS `ip_news`;
CREATE TABLE `ip_news`  (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `news_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `news_ignore` int(1) NULL DEFAULT 0 COMMENT '1表示忽略',
  `news_status` int(1) NULL DEFAULT NULL COMMENT '0未读\n1已读',
  `news_create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`news_id`) USING BTREE,
  INDEX `userid_index`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 144 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_notice
-- ----------------------------
DROP TABLE IF EXISTS `ip_notice`;
CREATE TABLE `ip_notice`  (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_title` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notice_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notice_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `notice_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notice_create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `notice_update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_province
-- ----------------------------
DROP TABLE IF EXISTS `ip_province`;
CREATE TABLE `ip_province`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province_name` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_result
-- ----------------------------
DROP TABLE IF EXISTS `ip_result`;
CREATE TABLE `ip_result`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NULL DEFAULT NULL,
  `group_file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_grade` int(3) NULL DEFAULT NULL,
  `suggest` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_school
-- ----------------------------
DROP TABLE IF EXISTS `ip_school`;
CREATE TABLE `ip_school`  (
  `id` int(4) NOT NULL,
  `school_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `school_address` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_teacher
-- ----------------------------
DROP TABLE IF EXISTS `ip_teacher`;
CREATE TABLE `ip_teacher`  (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_title
-- ----------------------------
DROP TABLE IF EXISTS `ip_title`;
CREATE TABLE `ip_title`  (
  `title_id` int(11) NOT NULL AUTO_INCREMENT,
  `title_status` int(1) NULL DEFAULT NULL COMMENT '1:正在使用，0:不使用',
  `title_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `title_create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `title_update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`title_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_user
-- ----------------------------
DROP TABLE IF EXISTS `ip_user`;
CREATE TABLE `ip_user`  (
  `user_id` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_passwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_sex` int(1) NULL DEFAULT NULL COMMENT '0：男  1：女',
  `user_education` int(1) NULL DEFAULT NULL COMMENT '学历 0高职/大专 1本科 2研究生 3博士',
  `user_school` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_school_id` int(11) NULL DEFAULT NULL,
  `user_academy` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_academy_id` int(11) NULL DEFAULT NULL,
  `user_profession` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
  `user_stu_num` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `user_grade` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_portrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `user_create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `user_update_time` datetime(0) NULL DEFAULT NULL,
  `user_status` int(1) NULL DEFAULT NULL COMMENT '0:未认证，1:已认证,2:待审核,3:拒绝',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username_unique`(`user_name`, `user_email`) USING BTREE,
  INDEX `userId_index`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_user_edu
-- ----------------------------
DROP TABLE IF EXISTS `ip_user_edu`;
CREATE TABLE `ip_user_edu`  (
  `edu_id` int(11) NOT NULL AUTO_INCREMENT,
  `edu_name` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`edu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ip_user_file
-- ----------------------------
DROP TABLE IF EXISTS `ip_user_file`;
CREATE TABLE `ip_user_file`  (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_type` int(1) NULL DEFAULT NULL COMMENT '0是比赛详情附件，1是用户作品',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cpt_id` int(11) NULL DEFAULT NULL,
  `file_status` int(1) NULL DEFAULT NULL COMMENT '文件阅读状态，0未读，1已读，2拒绝',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
