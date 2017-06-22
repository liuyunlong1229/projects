/*
SQLyog v10.2 
MySQL - 5.1.21-beta-community : Database - shiro_demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shiro_demo` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `shiro_easyui`;

/*Table structure for table `t_sys_org_resource` */

DROP TABLE IF EXISTS `t_sys_org_resource`;

CREATE TABLE `t_sys_org_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `createDttm` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `enableFlag` bit(1) NOT NULL,
  `lastUpdateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lastUpdateDttm` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `orgId` bigint(20) DEFAULT NULL,
  `rsId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7D2ACC06DABE7D2F` (`rsId`),
  KEY `FK7D2ACC063008DF1A` (`orgId`),
  CONSTRAINT `FK7D2ACC063008DF1A` FOREIGN KEY (`orgId`) REFERENCES `t_sys_organization` (`id`),
  CONSTRAINT `FK7D2ACC06DABE7D2F` FOREIGN KEY (`rsId`) REFERENCES `t_sys_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_sys_org_resource` */

/*Table structure for table `t_sys_organization` */

DROP TABLE IF EXISTS `t_sys_organization`;

CREATE TABLE `t_sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `createDttm` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `enableFlag` bit(1) NOT NULL,
  `lastUpdateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lastUpdateDttm` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `label` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`label`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_sys_organization` */

/*Table structure for table `t_sys_resource` */

DROP TABLE IF EXISTS `t_sys_resource`;

CREATE TABLE `t_sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `createDttm` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `enableFlag` bit(1) NOT NULL,
  `lastUpdateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lastUpdateDttm` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `isLeaf` bigint(20) DEFAULT NULL,
  `label` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `resourcePath` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `resourceType` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`label`),
  KEY `FK3506E76BDA8A9F9E` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_sys_resource` */

insert  into `t_sys_resource`(`id`,`createBy`,`createDttm`,`description`,`enableFlag`,`lastUpdateBy`,`lastUpdateDttm`,`version`,`icon`,`isLeaf`,`label`,`name`,`resourcePath`,`resourceType`,`pid`) values (1,NULL,'2015-04-24 15:34:24',NULL,'',NULL,'2015-04-24 15:34:30',1,NULL,NULL,'system:list','系统管理','system/list','menu',NULL),(2,NULL,'2015-04-23 16:58:29',NULL,'',NULL,'2015-04-24 16:58:38',1,NULL,NULL,'user:list','用户管理','user/home','menu',1),(3,NULL,'2015-04-23 17:00:05',NULL,'',NULL,'2015-04-24 17:00:11',1,NULL,NULL,'user:add','用户新增','user/add','button',2),(4,NULL,'2015-04-23 17:00:42',NULL,'',NULL,'2015-04-23 17:00:49',1,NULL,NULL,'user:edit','用户修改','user/edit','button',2),(5,NULL,'2015-04-23 17:01:15',NULL,'',NULL,'2015-04-23 17:01:21',1,NULL,NULL,'user:delete','用户删除','user/delete','button',2),(6,NULL,'2015-04-24 17:06:11',NULL,'',NULL,'2015-04-23 17:06:18',1,NULL,NULL,'role:list','角色管理','role/home','menu',1),(7,NULL,'2015-04-23 17:07:56',NULL,'',NULL,'2015-04-24 17:08:03',1,NULL,NULL,'role:add','角色新增','role/add','button',6),(8,NULL,'2015-04-23 17:08:34',NULL,'',NULL,'2015-04-23 17:08:40',1,NULL,NULL,'role:edit','角色修改','role/edit','button',6),(9,NULL,'2015-04-30 17:10:22',NULL,'',NULL,'2015-04-23 17:10:28',1,NULL,NULL,'role:delete','角色删除','role/delete','button',6),(10,NULL,'2015-04-23 17:11:49',NULL,'',NULL,'2015-04-23 17:11:56',1,NULL,NULL,'resource:list','资源管理','resource/home','menu',1),(11,NULL,'2015-04-24 17:29:27',NULL,'',NULL,'2015-04-30 17:29:34',1,NULL,NULL,'resource:add','资源新增','resource/add','button',10),(12,NULL,'2015-04-24 17:30:19',NULL,'',NULL,'2015-04-23 17:30:25',1,NULL,NULL,'resource:edit','资源修改','resource/edit','button',10),(13,NULL,'2015-04-23 17:30:59',NULL,'',NULL,'2015-04-23 17:31:05',1,NULL,NULL,'resource:delete','资源删除','resource/delete','button',10);

/*Table structure for table `t_sys_role` */

DROP TABLE IF EXISTS `t_sys_role`;

CREATE TABLE `t_sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `createDttm` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `enableFlag` bit(1) NOT NULL,
  `lastUpdateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lastUpdateDttm` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `label` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `label` (`label`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_sys_role` */

insert  into `t_sys_role`(`id`,`createBy`,`createDttm`,`description`,`enableFlag`,`lastUpdateBy`,`lastUpdateDttm`,`version`,`label`,`name`) values (1,NULL,NULL,NULL,'',NULL,'2015-04-23 16:56:21',1,'admin','管理员'),(2,NULL,NULL,'','',NULL,NULL,2,'tester','测试员'),(3,NULL,NULL,NULL,'',NULL,'2015-04-30 15:48:17',1,'developer','开发人员');

/*Table structure for table `t_sys_role_resource` */

DROP TABLE IF EXISTS `t_sys_role_resource`;

CREATE TABLE `t_sys_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `createDttm` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `enableFlag` bit(1) NOT NULL,
  `lastUpdateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lastUpdateDttm` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `rsid` bigint(20) DEFAULT NULL,
  `rid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE576F07AAB5C2E08` (`rid`),
  KEY `FKE576F07ADABE7D2F` (`rsid`),
  CONSTRAINT `FKE576F07AAB5C2E08` FOREIGN KEY (`rid`) REFERENCES `t_sys_role` (`id`),
  CONSTRAINT `FKE576F07ADABE7D2F` FOREIGN KEY (`rsid`) REFERENCES `t_sys_resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_sys_role_resource` */

insert  into `t_sys_role_resource`(`id`,`createBy`,`createDttm`,`description`,`enableFlag`,`lastUpdateBy`,`lastUpdateDttm`,`version`,`rsid`,`rid`) values (1,NULL,NULL,NULL,'',NULL,'2015-04-23 16:58:04',1,1,1),(2,NULL,NULL,NULL,'',NULL,NULL,NULL,2,1),(3,NULL,NULL,NULL,'',NULL,NULL,NULL,3,1),(4,NULL,NULL,NULL,'',NULL,NULL,NULL,4,1),(5,NULL,NULL,NULL,'',NULL,NULL,NULL,5,1),(6,NULL,NULL,NULL,'',NULL,NULL,NULL,6,1),(7,NULL,NULL,NULL,'',NULL,NULL,NULL,7,1),(8,NULL,NULL,NULL,'',NULL,NULL,NULL,8,1),(9,NULL,NULL,NULL,'',NULL,NULL,NULL,9,1),(10,NULL,NULL,NULL,'',NULL,NULL,NULL,10,1),(11,NULL,NULL,NULL,'',NULL,NULL,NULL,11,1),(12,NULL,NULL,NULL,'',NULL,NULL,NULL,12,1),(13,NULL,NULL,NULL,'',NULL,NULL,NULL,13,1),(25,NULL,NULL,NULL,'',NULL,NULL,0,1,2),(26,NULL,NULL,NULL,'',NULL,NULL,0,2,2),(27,NULL,NULL,NULL,'',NULL,NULL,0,10,2),(28,NULL,NULL,NULL,'',NULL,NULL,0,6,2);

/*Table structure for table `t_sys_user` */

DROP TABLE IF EXISTS `t_sys_user`;

CREATE TABLE `t_sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `createDttm` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `enableFlag` bit(1) NOT NULL,
  `lastUpdateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lastUpdateDttm` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `loginName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `realName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `salt` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `telephone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginName` (`loginName`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_sys_user` */

insert  into `t_sys_user`(`id`,`createBy`,`createDttm`,`description`,`enableFlag`,`lastUpdateBy`,`lastUpdateDttm`,`version`,`address`,`email`,`loginName`,`password`,`realName`,`salt`,`sex`,`telephone`) values (1,NULL,'2015-04-28 14:24:23','最高权限的用户','',NULL,NULL,1,NULL,'122222@qq.com','admin','5c2434ca7464c8ed6e7a33b9d2d7e04f','管理员','bbd267e349387b6b6317448d3f7c97b2','1','13213434567'),(2,NULL,NULL,'测试用户','',NULL,NULL,2,'上海市松江区','55555555@qq.com','zs','6c4f60432a2bb1222e2dff3538c393bb','张三',NULL,'1','13645787654'),(3,NULL,NULL,'测试用户','',NULL,NULL,6,'李四改成了男的','444444@qq.com','ls','c50d3f3d1ca3ee4fc63fe3123a24f93b','李四','b3c77a48ce72384e3dbec0d497b06345','1','13856899876'),(4,NULL,'2015-04-28 14:24:30','测试用户','',NULL,NULL,1,NULL,'5555555@qq.com','ww','86668b1ac75db91c7b41482c4da6a215','王五','23a51bab067b4b9263862052e9512f8f','1','18654656777'),(5,NULL,'2015-04-28 14:24:29','测试用户','',NULL,NULL,1,NULL,'4444442@qq.com','ml','6976b364a9ed84f39272f5a4458005d4','马六','718fc5dfe81c0b1b04480eb9c200578e','0','18256765459'),(6,NULL,NULL,NULL,'',NULL,NULL,0,'dddd','3333@qq.com','aa','3a6289d5de368c57e3214a683c8260a1','aa','6c47c482bac4202177f1a1531a8c70f8','1','333333'),(7,NULL,NULL,NULL,'',NULL,NULL,0,'ggg','2222@qq.com','bb','9b536216c0b5defd6578358c8cdfbf04','bb','92d190e6d34f8457964f8f8de9df70fd','1','22222222'),(28,NULL,NULL,NULL,'',NULL,NULL,0,'上海市徐汇区','55555@qq.com','cc','85be41508c4bdb0e54d6071b39bf87da','cc','116a7830741ddad49219ce775fd46bb9','0','333333'),(29,NULL,NULL,'软件测试人员','',NULL,NULL,0,'湖南省衡阳市','333333@qq.com','test','187c29cbfaebb0aaacb0512c743c2dd3','测试员','1f16ac0cd62c7340d78e139fd3a1657b','1','13134567654');

/*Table structure for table `t_sys_user_organization` */

DROP TABLE IF EXISTS `t_sys_user_organization`;

CREATE TABLE `t_sys_user_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `createDttm` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `enableFlag` bit(1) NOT NULL,
  `lastUpdateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lastUpdateDttm` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `uId` bigint(20) DEFAULT NULL,
  `orgId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5005626AAB5DA4A0` (`uId`),
  KEY `FK5005626A3008DF1A` (`orgId`),
  CONSTRAINT `FK5005626A3008DF1A` FOREIGN KEY (`orgId`) REFERENCES `t_sys_organization` (`id`),
  CONSTRAINT `FK5005626AAB5DA4A0` FOREIGN KEY (`uId`) REFERENCES `t_sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_sys_user_organization` */

/*Table structure for table `t_sys_user_role` */

DROP TABLE IF EXISTS `t_sys_user_role`;

CREATE TABLE `t_sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `createDttm` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `enableFlag` bit(1) NOT NULL,
  `lastUpdateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lastUpdateDttm` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `rid` bigint(20) DEFAULT NULL,
  `uid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF8082CADAB5DA4A0` (`uid`),
  KEY `FKF8082CADAB5C2E08` (`rid`),
  CONSTRAINT `FKF8082CADAB5C2E08` FOREIGN KEY (`rid`) REFERENCES `t_sys_role` (`id`),
  CONSTRAINT `FKF8082CADAB5DA4A0` FOREIGN KEY (`uid`) REFERENCES `t_sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_sys_user_role` */

insert  into `t_sys_user_role`(`id`,`createBy`,`createDttm`,`description`,`enableFlag`,`lastUpdateBy`,`lastUpdateDttm`,`version`,`rid`,`uid`) values (1,NULL,'2015-04-23 16:57:25',NULL,'',NULL,'2015-04-23 16:57:34',1,1,1),(2,NULL,NULL,NULL,'',NULL,NULL,1,2,28),(3,NULL,NULL,NULL,'',NULL,NULL,1,3,28),(6,NULL,NULL,NULL,'',NULL,NULL,0,3,3),(8,NULL,NULL,NULL,'',NULL,NULL,0,2,2),(9,NULL,NULL,NULL,'',NULL,NULL,1,2,29);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
