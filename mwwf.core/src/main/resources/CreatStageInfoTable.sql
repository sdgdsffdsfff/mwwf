CREATE TABLE  IF NOT EXISTS `stage_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flowId` int(11) DEFAULT NULL,
  `flowName` varchar(200) DEFAULT NULL,
  `stageName` varchar(200) DEFAULT NULL,
  `stageStatus` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `del` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;