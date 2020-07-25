-- MySQL dump 10.13  Distrib 8.0.20, for macos10.15 (x86_64)
--
-- Host: common101.my.2dfire-daily.com    Database: cpt
-- ------------------------------------------------------
-- Server version	5.7.23-log

-- DROP DATABASE IF EXISTS `cpt`;
-- CREATE DATABASE `cpt`;
-- USE `cpt`;


SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '65a2b4eb-d393-11e5-b12c-525400497fb2:1-212739752:212771456-213150216,
ca3d3488-7fa8-11ea-a06e-02000a0116af:1-8754873';

--
-- Table structure for table `app`
--

DROP TABLE IF EXISTS `app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app` (
  `id` bigint(18) NOT NULL,
  `name` varchar(45) DEFAULT NULL COMMENT '名称',
  `version` varchar(45) DEFAULT NULL COMMENT '版本',
  `package_name` varchar(45) DEFAULT NULL COMMENT '包名',
  `type` varchar(45) DEFAULT NULL,
  `uploader` varchar(45) DEFAULT NULL COMMENT '上传人',
  `remarks` varchar(45) DEFAULT NULL COMMENT '备注',
  `package_address` varchar(200) DEFAULT NULL,
  `is_valid` tinyint(2) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL COMMENT '创建时间',
  `modify_time` varchar(45) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app`
--

LOCK TABLES `app` WRITE;
/*!40000 ALTER TABLE `app` DISABLE KEYS */;
INSERT INTO `app` VALUES (1,'CaseDesk','5.8.5','com.zmsoft.eatery.cashdesk.activity',NULL,'lianyu','调试数据',NULL,0,NULL,'2019-06-20 11:49:09'),(55551285415,'KDS（1）','2.2.2','com.zmsoft.kds','1','lianyu','调试数据',NULL,1,'2019-06-18 15:42:54','2019-06-20 12:06:04'),(55552199371,'KDS','2.2.4','com.zmsoft.kds','1','lianyu','调试数据',NULL,1,'2019-06-18 15:40:22','2019-06-18 15:40:22'),(55552455426,'CashDesk','5.8.7','com.zmsoft.eatery.cashdesk.activity','1','lianyu','调试数据',NULL,1,'2019-06-18 15:45:05','2019-06-18 15:45:05'),(55552591519,'CashDesk','5.8.6','com.zmsoft.eatery.cashdesk.activity','1','lianyu','调试数据',NULL,1,'2019-06-18 15:45:01','2019-06-18 15:45:01'),(55554059897,'KDS','2.2.3','com.zmsoft.kds','1','lianyu','调试数据',NULL,1,'2019-06-18 15:40:18','2019-06-18 15:40:18'),(55554529407,'KDS','2.2.0','com.zmsoft.kds','1','lianyu','调试数据',NULL,1,'2019-06-18 12:04:49','2019-06-18 12:04:49'),(55554796207,'CashDesk','5.6.99','com.zmsoft.eatery.cashdesk.activity','1','lianyu','调试数据',NULL,1,'2019-06-18 15:45:12','2019-06-18 15:45:12'),(55554835122,'KDS','2.2.5','com.zmsoft.kds','1','lianyu','调试数据',NULL,1,'2019-06-18 15:40:26','2019-06-18 15:40:26'),(55555451560,'KDS','2.2.6','com.zmsoft.kds','1','lianyu','调试数据',NULL,1,'2019-06-18 15:40:30','2019-06-18 15:40:30'),(55556141421,'KDS','2.3.1','com.zmsoft.kds','1','lianyu','调试数据',NULL,1,'2019-06-18 15:42:50','2019-06-18 15:42:50'),(55556964679,'KDS','2.2.1','com.zmsoft.kds','1','lianyu','调试数据',NULL,1,'2019-06-18 12:05:34','2019-06-18 12:05:34'),(55556985556,'KDS','2.3.4','com.zmsoft.kds','1','lianyu','调试数据',NULL,1,'2019-06-18 15:43:01','2019-06-18 15:43:01'),(55557264447,'CashDesk','5.8.51','com.zmsoft.eatery.cashdesk.activity','1','lianyu','调试数据',NULL,1,'2019-06-18 15:44:55','2019-06-18 15:44:55'),(55557573430,'KDS','2.3.5','com.zmsoft.kds','1','lianyu','调试数据',NULL,1,'2019-06-18 15:43:04','2019-06-18 15:43:04'),(55558705854,'KDS','2.3.3','com.zmsoft.kds','1','lianyu','调试数据',NULL,1,'2019-06-18 15:42:58','2019-06-18 15:42:58'),(55558820502,'KDS','2.2.2','com.zmsoft.kds','1','lianyu','调试数据',NULL,1,'2019-06-18 12:05:50','2019-06-18 12:05:50'),(555514693750,'KDS','3.3.3','com.zmsoft.kds','1','lianyu','接口调试','暂无',1,'2019-09-23 17:32:39','2019-09-23 17:32:39');
/*!40000 ALTER TABLE `app` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;


--
-- Table structure for table `case`
--

DROP TABLE IF EXISTS `case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `case` (
  `id` bigint(18) NOT NULL,
  `name` varchar(45) DEFAULT NULL COMMENT '用例标题',
  `precondition` varchar(45) DEFAULT NULL COMMENT '前置条件',
  `step` varchar(500) DEFAULT NULL COMMENT '测试步骤',
  `desired_result` varchar(200) DEFAULT NULL COMMENT '期望结果',
  `script_address` varchar(45) DEFAULT NULL COMMENT '脚本地址',
  `creator` varchar(45) DEFAULT '' COMMENT '创建人',
  `modifier` varchar(45) DEFAULT '' COMMENT '修改人',
  `remarks` varchar(200) DEFAULT '' COMMENT '备注',
  `is_valid` tinyint(2) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL COMMENT '创建时间',
  `modify_time` varchar(45) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用例表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `case`
--

LOCK TABLES `case` WRITE;
/*!40000 ALTER TABLE `case` DISABLE KEYS */;
INSERT INTO `case` VALUES (555554059989098896,'互联网-KDS支付宝扫码登录','支付宝账号已注册火掌柜','1.点击支付宝扫码登录，进入支付宝登录二维码；2.打开授权宝扫一扫；3.支付宝识别二维码后，确认登录','登录成功','','lianyu','lianyu','调试数据',1,'2019-09-23 18:00:23','2019-09-23 18:00:23'),(555578244145144559,'互联网-KDS手机号密码登录','手机号密码正确','1.输入手机号和密码；2.点击登录按钮','登录成功','','lianyu','lianyu','调试数据',1,'2019-09-23 17:45:52','2019-09-23 17:45:52');
/*!40000 ALTER TABLE `case` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;


--
-- Table structure for table `devices`
--

DROP TABLE IF EXISTS `devices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `devices` (
  `id` bigint(18) NOT NULL,
  `device_name` varchar(45) DEFAULT NULL COMMENT '设备名称',
  `system_type` tinyint(2) DEFAULT '1' COMMENT '系统类型(1.Android, 2.iOS)',
  `system_version` varchar(45) DEFAULT NULL COMMENT '系统版本',
  `core` tinyint(2) DEFAULT NULL COMMENT '核心数',
  `ram` varchar(45) DEFAULT '' COMMENT '内存大小',
  `network` tinyint(2) DEFAULT '1' COMMENT '网络状态（0.有线, 1.Wi-Fi, 2.2G, 3.3G, 4.4G, 5.5G）',
  `ip` varchar(20) DEFAULT '' COMMENT 'IP地址',
  `serial_number` varchar(20) DEFAULT '' COMMENT '序列号，简称SN',
  `mac_address` varchar(45) DEFAULT NULL COMMENT 'MAC地址',
  `brand` varchar(45) DEFAULT NULL COMMENT '品牌',
  `model` varchar(45) DEFAULT '' COMMENT '型号',
  `resolution` varchar(45) DEFAULT NULL COMMENT '分辨率',
  `connect_status` tinyint(2) DEFAULT '1' COMMENT 'ADB连接状态（1。device(设备已连接), 0.offline（表示设备未连接成功或无响应）, -1.no device（没有设备/模拟器连接））',
  `use_status` tinyint(2) DEFAULT '0' COMMENT '使用状态（0.空闲, 1.使用中）',
  `is_valid` tinyint(2) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL COMMENT '创建时间',
  `modify_time` varchar(45) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sn` (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devices`
--

LOCK TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices` VALUES (55551807276,NULL,1,'4.4.2',4,'1048576',NULL,'10.1.135.139','CNDFP616120500002895','44:2c:05:fd:9e:2d','rockchip','rk3188','768x1360',-1,0,1,'2019-09-12 08:47:49','2019-10-30 11:05:31'),(555565986406196922,NULL,1,'4.4.2',1,'1048576',NULL,'','CNDFP6160503000003D0','e0:76:d0:ce:71:31','rockchip','rk3188','768x1360',-1,0,1,'2019-09-25 10:09:55','2019-10-30 11:05:31');
/*!40000 ALTER TABLE `devices` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;


--
-- Table structure for table `execution_plan`
--

DROP TABLE IF EXISTS `execution_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `execution_plan` (
  `id` bigint(18) NOT NULL,
  `app_id` bigint(18) DEFAULT NULL COMMENT '应用ID',
  `devices_id` bigint(18) DEFAULT NULL COMMENT '设备ID',
  `plan_name` varchar(45) DEFAULT NULL COMMENT '计划名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `tester` varchar(45) DEFAULT NULL COMMENT '测试人',
  `start_time` varchar(45) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(45) DEFAULT NULL COMMENT '结束时间',
  `is_valid` tinyint(2) DEFAULT '1' COMMENT '是否有效（1，有效；0无效；默认有效）',
  `create_time` varchar(45) DEFAULT NULL COMMENT '创建时间',
  `modify_time` varchar(45) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='执行计划表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `execution_plan`
--

LOCK TABLES `execution_plan` WRITE;
/*!40000 ALTER TABLE `execution_plan` DISABLE KEYS */;
INSERT INTO `execution_plan` VALUES (555512032543666181,555514693750,555565986406196922,'测试计划01','','鲢鱼','2019-10.12 11:51:00','2019-10.13 11:51:00',1,'2019-10-25 10:53:05','2019-10-25 10:53:05'),(555514330801852458,555514693750,555565986406196922,'测试计划01','','鲢鱼','2019-10.12 11:51:00','2019-10.13 11:51:00',1,'2019-10-12 11:54:10','2019-10-12 11:54:10'),(555536417330169483,555514693750,555565986406196922,'测试计划01','','鲢鱼','2019-10.12 11:51:00','2019-10.13 11:51:00',1,'2019-10-25 10:48:49','2019-10-25 10:48:49'),(555550366796761845,555514693750,555565986406196922,'测试计划01','','鲢鱼','2019-10.12 11:51:00','2019-10.13 11:51:00',1,'2019-10-25 10:57:37','2019-10-25 10:57:37'),(555550798617983104,55558820502,55551807276,'执行计划002','接口调试～～','鲢鱼','2019-10-16 09:59:59','2019-10-17 09:59:59',0,'2019-10-16 11:07:41','2019-10-17 14:58:18'),(555557684339336690,55558820502,55551807276,'执行计划002','接口调试～～','鲢鱼','2019-10-16 09:59:59','2019-10-17 09:59:59',0,'2019-10-16 11:30:24','2019-10-17 15:20:20'),(555559475588217611,555514693750,555565986406196922,'测试计划01','','鲢鱼','2019-10.12 11:51:00','2019-10.13 11:51:00',1,'2019-10-25 10:49:29','2019-10-25 10:49:29'),(555589862865953478,55558820502,55551807276,'执行计划0021','接口调试～','鲢鱼','2019-10-16 09:59:59','2019-10-17 09:59:59',0,'2019-10-16 16:00:05','2019-10-17 14:57:50'),(555589862865953479,55558820502,55551807276,'执行计划002','接口调试～～','鲢鱼','2019-10-16 09:59:59','2019-10-17 09:59:59',0,'2019-10-16 10:05:15','2019-10-25 10:57:37');
/*!40000 ALTER TABLE `execution_plan` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;


--
-- Table structure for table `execution_record`
--

DROP TABLE IF EXISTS `execution_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `execution_record` (
  `id` bigint(18) NOT NULL,
  `app_id` bigint(18) DEFAULT NULL,
  `environment_id` bigint(18) DEFAULT NULL,
  `task_id` bigint(11) DEFAULT NULL COMMENT '执行任务ID',
  `is_valid` tinyint(2) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL COMMENT '创建时间',
  `modify_time` varchar(45) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='执行记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `execution_record`
--

LOCK TABLES `execution_record` WRITE;
/*!40000 ALTER TABLE `execution_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `execution_record` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;


--
-- Table structure for table `execution_task`
--

DROP TABLE IF EXISTS `execution_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `execution_task` (
  `id` bigint(11) NOT NULL COMMENT '任务ID',
  `threads` varchar(45) DEFAULT NULL COMMENT '线程数',
  `frequency` varchar(45) DEFAULT NULL COMMENT '执行次数',
  `interval` varchar(45) DEFAULT NULL COMMENT '执行间隔',
  `forever` tinyint(4) DEFAULT '0' COMMENT '永久执行（1.是；0.否；默认否）',
  `plan_id` bigint(11) DEFAULT NULL COMMENT '执行计划ID',
  `is_valid` tinyint(4) DEFAULT '1' COMMENT '是否有效（1.有效；0.无效；默认有效）',
  `create_time` varchar(45) DEFAULT NULL COMMENT '创建时间',
  `modify_time` varchar(45) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='执行任务表（已废弃）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `execution_task`
--

LOCK TABLES `execution_task` WRITE;
/*!40000 ALTER TABLE `execution_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `execution_task` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;


--
-- Table structure for table `per_frequency`
--

DROP TABLE IF EXISTS `per_frequency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `per_frequency` (
  `id` bigint(18) NOT NULL,
  `project_id` bigint(18) DEFAULT NULL,
  `plan_id` bigint(18) DEFAULT NULL,
  `frequency` varchar(45) DEFAULT NULL COMMENT '获取次数',
  `interval` varchar(45) DEFAULT NULL COMMENT '间隔时间',
  `start_time` varchar(45) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(45) DEFAULT NULL COMMENT '结束时间',
  `is_valid` tinyint(2) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL COMMENT '创建时间',
  `modify_time` varchar(45) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='性能获取频率表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `per_frequency`
--

LOCK TABLES `per_frequency` WRITE;
/*!40000 ALTER TABLE `per_frequency` DISABLE KEYS */;
/*!40000 ALTER TABLE `per_frequency` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;


--
-- Table structure for table `performance`
--

DROP TABLE IF EXISTS `performance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `performance` (
  `id` bigint(18) NOT NULL,
  `frequency_id` bigint(18) DEFAULT NULL,
  `project_id` bigint(18) DEFAULT NULL,
  `execution_plan_id` bigint(18) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL COMMENT '数据类型（1.CPU；2.内存；3.流量；4.电量；5.温度）',
  `value` varchar(45) DEFAULT NULL COMMENT '值',
  `create_time` varchar(45) DEFAULT NULL COMMENT '创建时间',
  `modify_time` varchar(45) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='性能信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance`
--

LOCK TABLES `performance` WRITE;
/*!40000 ALTER TABLE `performance` DISABLE KEYS */;
/*!40000 ALTER TABLE `performance` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;


--
-- Table structure for table `plan_case`
--

DROP TABLE IF EXISTS `plan_case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan_case` (
  `id` bigint(18) NOT NULL,
  `plan_id` bigint(18) DEFAULT NULL,
  `case_id` bigint(18) DEFAULT NULL,
  `is_valid` tinyint(2) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL COMMENT '创建时间',
  `modify_time` varchar(45) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试计划和用例关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_case`
--

LOCK TABLES `plan_case` WRITE;
/*!40000 ALTER TABLE `plan_case` DISABLE KEYS */;
INSERT INTO `plan_case` VALUES (555515667960227117,555514330801852458,555554059989098896,1,'2019-10-12 14:33:15','2019-10-12 14:33:15'),(555517389835058088,555514330801852458,555578244145144559,1,'2019-10-25 10:48:48','2019-10-25 10:48:48'),(555521154257918241,555589862865953479,555554059989098896,0,'2019-10-16 17:41:43','2019-10-16 18:48:01'),(555525824580564280,555589862865953479,555554059989098896,0,'2019-10-16 17:52:20','2019-10-16 18:48:01'),(555526644766830336,555514330801852458,555578244145144559,1,'2019-10-25 10:53:05','2019-10-25 10:53:05'),(555527448349020708,555589862865953479,555554059989098896,0,'2019-10-16 17:50:08','2019-10-16 18:48:01'),(555530409246726545,555514330801852458,555578244145144559,1,'2019-10-25 10:57:37','2019-10-25 10:57:37'),(555530908958701035,555589862865953479,555578244145144559,1,'2019-10-16 18:48:01','2019-10-16 18:48:01'),(555536148768696255,555589862865953479,555554059989098896,0,'2019-10-16 17:46:16','2019-10-16 18:48:01'),(555539100319027703,555589862865953479,555554059989098896,0,'2019-10-16 18:45:24','2019-10-16 18:48:01'),(555539721517074881,555514330801852458,555578244145144559,1,'2019-10-25 10:49:29','2019-10-25 10:49:29'),(555540117378463499,555557684339336690,555554059989098896,0,'2019-10-16 11:30:24','2019-10-17 15:20:20'),(555547499759577986,555557684339336690,555578244145144559,0,'2019-10-16 11:30:24','2019-10-17 15:20:20'),(555553568898410029,555589862865953479,555578244145144559,0,'2019-10-16 10:05:15','2019-10-16 18:46:54'),(555555500662463797,555589862865953479,555554059989098896,0,'2019-10-16 17:59:50','2019-10-16 18:48:01'),(555579343614293864,555589862865953479,555554059989098896,0,'2019-10-16 10:05:15','2019-10-16 18:48:01'),(555587394201010440,555514330801852458,555578244145144559,1,'2019-10-12 14:33:36','2019-10-12 14:33:36');
/*!40000 ALTER TABLE `plan_case` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;


--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` bigint(18) NOT NULL,
  `name` varchar(45) DEFAULT NULL COMMENT '项目名称',
  `tester` varchar(45) DEFAULT NULL COMMENT '测试人',
  `status` int(11) DEFAULT NULL COMMENT '项目当前状态（0.新建，1.开始，2.进行中，3.暂停，4.完成，5.延后）',
  `start_time` varchar(45) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(45) DEFAULT NULL COMMENT '结束时间',
  `is_valid` tinyint(2) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL COMMENT '创建时间',
  `modify_time` varchar(45) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;