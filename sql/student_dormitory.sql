/*
 Navicat Premium Data Transfer

 Source Server         : windows_mysql
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : student_dormitory

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 22/02/2025 21:11:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色标识',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin', '管理员', 'http://localhost:9090/files/1697438073596-avatar.png', 'ADMIN', '13677889922', 'admin@xm.com');

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '位置',
  `num` int NULL DEFAULT 0 COMMENT '宿舍数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '宿舍楼信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES (1, 'N-1栋', '北区生活区', 1);
INSERT INTO `building` VALUES (2, 'E-1栋', '东区生活区', 1);
INSERT INTO `building` VALUES (3, 'E-2栋', '东区生活区', 0);

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '寝室号',
  `building_id` int NULL DEFAULT NULL COMMENT '宿舍楼ID',
  `num` int NULL DEFAULT NULL COMMENT '容纳数',
  `now_num` int NULL DEFAULT 0 COMMENT '已住人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '宿舍信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES (1, 'E-101', 2, 4, 4);
INSERT INTO `dormitory` VALUES (2, 'N-101', 1, 4, 0);

-- ----------------------------
-- Table structure for electro
-- ----------------------------
DROP TABLE IF EXISTS `electro`;
CREATE TABLE `electro`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dormitory_id` int NULL DEFAULT NULL COMMENT '宿舍ID',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '费用',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '费用说明',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '缴费状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '电费缴纳表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of electro
-- ----------------------------
INSERT INTO `electro` VALUES (1, 1, 100.00, '电费 + 水费', '已缴费');

-- ----------------------------
-- Table structure for exchanges
-- ----------------------------
DROP TABLE IF EXISTS `exchanges`;
CREATE TABLE `exchanges`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `studenta` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学生A姓名',
  `studentb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学生B姓名',
  `dormitorya` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学生A原寝室',
  `dormitoryb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学生B原寝室',
  `beda` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学生A原床位',
  `bedb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学生B原床位',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更换时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '换寝记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exchanges
-- ----------------------------
INSERT INTO `exchanges` VALUES (1, '路飞', '李星云', 'E-101', 'E-101', '1', '2', '2025-02-22 11:02:10');
INSERT INTO `exchanges` VALUES (2, '李星云', '蔡寻鲲', 'E-101', 'E-101', '1', '3', '2025-02-22 11:05:37');
INSERT INTO `exchanges` VALUES (3, '路飞', '蔡寻鲲', 'E-101', 'E-101', '2', '1', '2025-02-22 11:09:49');
INSERT INTO `exchanges` VALUES (4, '李星云', '蔡寻鲲', 'E-101', 'E-101', '3', '2', '2025-02-22 11:21:00');
INSERT INTO `exchanges` VALUES (5, '李星云', '路飞', 'E-101', 'E-101', '2', '1', '2025-02-22 11:21:30');
INSERT INTO `exchanges` VALUES (6, '路飞', '李星云', 'E-101', 'E-101', '2', '1', '2025-02-22 20:21:47');
INSERT INTO `exchanges` VALUES (7, '路飞', '李星云', 'E-101', 'E-101', '1', '2', '2025-02-22 20:22:30');
INSERT INTO `exchanges` VALUES (8, '路飞', '蔡寻鲲', 'E-101', 'E-101', '2', '3', '2025-02-22 20:29:27');

-- ----------------------------
-- Table structure for fix
-- ----------------------------
DROP TABLE IF EXISTS `fix`;
CREATE TABLE `fix`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` int NULL DEFAULT NULL COMMENT '学生ID',
  `dormitory_id` int NULL DEFAULT NULL COMMENT '宿舍ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '报修说明',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '报修图片',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '报修时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '处理状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '寝室报修表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fix
-- ----------------------------
INSERT INTO `fix` VALUES (1, 3, 1, '宿舍灯坏了', 'http://localhost:9090/files/1740224477512-xm.gif', '2025-02-22 19:41:19', '已处理');
INSERT INTO `fix` VALUES (2, 1, 1, '水龙头漏水', 'http://localhost:9090/files/1740224819974-ayst.gif', '2025-02-22 19:47:01', '已处理');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建时间',
  `user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '公告信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '今天系统正式上线，开始内测', '今天系统正式上线，开始内测', '2023-09-05', 'admin');
INSERT INTO `notice` VALUES (2, '所有功能都已完成，可以正常使用', '所有功能都已完成，可以正常使用', '2023-09-05', 'admin');
INSERT INTO `notice` VALUES (3, '今天天气很不错，可以出去一起玩了', '今天天气很不错，可以出去一起玩了', '2023-09-05', 'admin');

-- ----------------------------
-- Table structure for stay
-- ----------------------------
DROP TABLE IF EXISTS `stay`;
CREATE TABLE `stay`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` int NULL DEFAULT NULL COMMENT '学生ID',
  `dormitory_id` int NULL DEFAULT NULL COMMENT '寝室ID',
  `building_id` int NULL DEFAULT NULL COMMENT '宿舍楼ID',
  `bed` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '床位号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '住宿信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stay
-- ----------------------------
INSERT INTO `stay` VALUES (1, 3, 1, 2, '1');
INSERT INTO `stay` VALUES (2, 1, 1, 2, '3');
INSERT INTO `stay` VALUES (3, 2, 1, 2, '2');
INSERT INTO `stay` VALUES (4, 4, 1, 2, '4');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'STUDENT' COMMENT '角色',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学生信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'http://localhost:9090/files/1739674298061-lf.jpg', 'lf', '123', '路飞', 'STUDENT', '13467522226', 'lf@student_dormitory.com');
INSERT INTO `student` VALUES (2, 'http://localhost:9090/files/1739674380678-cxk.jpg', 'cxk', '123', '蔡寻鲲', 'STUDENT', '13219563881', 'cxk@student_dormitory.com');
INSERT INTO `student` VALUES (3, 'http://localhost:9090/files/1739675636765-lxy.png', 'lxy', '123', '李星云', 'STUDENT', '19122504198', 'lxy@student_dormitory.com');
INSERT INTO `student` VALUES (4, 'http://localhost:9090/files/1740229247755-jrx.png', 'jrx', '123', '姬如雪', 'STUDENT', NULL, NULL);

-- ----------------------------
-- Table structure for visit
-- ----------------------------
DROP TABLE IF EXISTS `visit`;
CREATE TABLE `visit`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dormitory_id` int NULL DEFAULT NULL COMMENT '宿舍ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '来访说明',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登记号码',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登记时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '来访登记表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of visit
-- ----------------------------
INSERT INTO `visit` VALUES (1, 1, '来看孩子', '18733525265', '2025-02-23 10:00:00');
INSERT INTO `visit` VALUES (2, 1, '看孩子的生活环境', '13379218118', '2025-02-23 15:00:00');

SET FOREIGN_KEY_CHECKS = 1;
