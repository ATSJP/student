/*
Navicat MySQL Data Transfer

Source Server         : sjp
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : web_book

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-31 21:24:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `id` varchar(40) NOT NULL,
  `cname` varchar(30) NOT NULL,
  `cpassword` varchar(40) NOT NULL,
  `cgender` varchar(5) DEFAULT NULL,
  `cbirth` varchar(15) DEFAULT NULL,
  `cmajority` varchar(30) DEFAULT NULL,
  `cinterest` varchar(50) DEFAULT NULL,
  `cemail` varchar(25) DEFAULT NULL,
  `cphone` varchar(30) NOT NULL,
  PRIMARY KEY (`cname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES ('dbed40b8-1394-46e4-9785-bb39a779e7d1', '丽萨', '123456', '男', '1992-01-02', '软件工程', '足球;台球;其他;', 'laoliweibo@sina.com', '17405570239');
INSERT INTO `customers` VALUES ('e0335332-9191-410a-b200-f66f1b90ca6b', '光头强', 'asdfcv', '男', '1050-09-25', '建筑学', '羽毛球;篮球;足球;', 'guantouqiang@flash.com', '17568945541');
INSERT INTO `customers` VALUES ('3c6617bb-b7d7-4749-b3fd-a06bf43d4978', '思科', 'ghj123', '女', '2001-10-01', '建筑学', '篮球;足球;游泳;羽毛球;排球;台球;拳击;其他;', '9654781235@outlook.con', '15250216456');
INSERT INTO `customers` VALUES ('8ede264b-562c-4f04-bbe8-5bf2a5308131', '李大大', 'gtygty56', '女', '1997-12-02', '小学教育', '足球;台球;其他;', 'lidadasina@sina.com', '13406570209');
INSERT INTO `customers` VALUES ('cb8495ee-f797-4d18-8596-0fb766603e07', '李忠', '123456', '女', '1965-05-01', '文学院', '羽毛球;足球;', '480725864@qq.com', '13406689975');
INSERT INTO `customers` VALUES ('8f2e09ef-07d1-40cb-9052-ebffd8ad0ec3', '测试', '123456', '男', '', '其他', '足球;其他;', '5129478512@163.com', '13405570209');
INSERT INTO `customers` VALUES ('39e06647-bda0-4303-aff4-53e9225b0b4f', '熊二', '520520', '男', '1001-02-02', '其他', '羽毛球;篮球;足球;游泳;', '456123789@outlook.com', '13564867591');
INSERT INTO `customers` VALUES ('7474d737-d72b-4d9f-a732-eea28712d686', '熊大', '250250', '男', '1000-01-01', '其他', '羽毛球;篮球;足球;', '25025025025@outlook.com', '15250250250');
INSERT INTO `customers` VALUES ('9596b928-8a3d-44c8-913c-778e2b1f87ea', '王', '123456', '女', '1992-02-01', '计算机与科学', '篮球;台球;其他;', '148562397@qq.com', '13264867596');
INSERT INTO `customers` VALUES ('e671b46e-a5e3-4433-a8ea-9b17fc5607d7', '王五', 'qwerdf', '女', '1985-02-09', '计算机与科学', '羽毛球;篮球;足球;游泳;', '11283919231@163.com', '15864567588');
INSERT INTO `customers` VALUES ('d6b24e6c-aef6-412a-98c3-8a62b6f4786e', '王大', '123456', '男', '1992-10-15', '软件工程', '篮球;足球;台球;拳击;其他;', 'wangda@outlook.com', '17405510219');
INSERT INTO `customers` VALUES ('15a5d82b-8e0a-4058-bd5b-0bcbb97ba957', '王老', '148159', '男', '1992-01-01', '软件工程', '篮球;足球;游泳;羽毛球;排球;台球;拳击;其他;', '5129478512@163.com', '13405570209');
INSERT INTO `customers` VALUES ('7ec06a97-26ae-4a3e-a2dc-90d31805d320', '王老二', '123789', '男', '1200-01-02', '电子信息', '篮球;游泳;台球;其他;', '147895623@qq.com', '13422312611');
INSERT INTO `customers` VALUES ('d33f1e2f-ecb8-4d19-b4ff-a55cefbe3dfd', '王老板', 'asdfgh', '男', '1992-05-03', '计算机与科学', '羽毛球;足球;游泳;', '5598949525@163.com', '13456899535');
INSERT INTO `customers` VALUES ('e81d9a2f-c903-420a-a1ed-f556f82fd16d', '王霞可', 'cf1cf2', '女', '1992-0102', '电子信息', '篮球;足球;排球;台球;其他;', '148262397@163.com', '18405510119');
INSERT INTO `customers` VALUES ('0bdb1fb7-4208-466a-8d00-df33f7840cbe', '蔡依林', 'hjkiop1', '女', '1992-02-06', '其他', '足球;游泳;台球;', 'caiyilin@outlook.com', '13264237596');
INSERT INTO `customers` VALUES ('101d202c-8023-4172-a60a-2eede35d3b7c', '蔡雯', '123qwe', '男', '1992-01-02', '网络工程', '篮球;排球;其他;', '148562397@163.com', '17405570219');
INSERT INTO `customers` VALUES ('8acf52a3-4cd7-4e11-ae43-c0b313154f58', '袁媛', 'sjoiu1', '男', '1991-07-02', '计算机与科学', '排球;其他;', '578856297@163.com', '17405021931');
INSERT INTO `customers` VALUES ('a6a57e58-8103-427e-bdc7-af777f443412', '阿三', 'asan520', '男', '1996-01-02', '软件工程', '足球;台球;拳击;其他;', '1485623297@163.com', '13557020956');
INSERT INTO `customers` VALUES ('89b6ff1d-b1ee-43d4-8397-a95a67878195', '阿斯', 'qwer1230', '男', '2007-01-01', '计算机与科学', '足球;台球;其他;', 'sda1asdad@163.com', '15403570247');
INSERT INTO `customers` VALUES ('84ffa587-a1ff-4c69-b90a-d68b0f5f543f', '高顿', 'qwer520qw', '男', '2005-12-21', '历史师范', '足球;台球;其他;', '987564123@sina.com', '15250255621');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(40) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`,`username`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('34f39b5a-eb8e-43ee-a486-0f82b5fa1732', 'sjppop', '124', '455@163.com');
INSERT INTO `users` VALUES ('933098ad-3737-423d-9cd7-733cfc6668e8', 'xfzhang', '123456', '');
INSERT INTO `users` VALUES ('d8f71490-7029-46a8-9da8-9a937010585e', 'admin', '123456', null);
INSERT INTO `users` VALUES ('d8f71490-7029-46a8-9da8-9a937060585e', 'Jack', '123456', '');
