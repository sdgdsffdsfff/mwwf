package com.suning.app.mwwf.dao;

import com.suning.app.mwwf.entity.StageInfoEntity;

public interface StageInfoDao {

	/**
	 * 更新流程引擎stage_info表
	 */
	//@Update("UPDATE `mwwf`.`stage_info` SET `id`=NULL, `flowInsId`=NULL, `flowName`=NULL, `stageName`=NULL, `stageStatus`=NULL, `createTime`=NULL, `updateTime`=NULL, `del`=NULL WHERE (ISNULL(`id`));")
	public Integer updateStageInfo(StageInfoEntity stageInfoEntity);

	/**
	 * 插入流程引擎stage_info表
	 */
	//@Insert("INSERT INTO `stage_info` (`flowInsId`, `flowName`, `stageName`, `stageStatus`, `createTime`) VALUES (#{flowInsId}, #{flowName}, #{stageName}, #{stageStatus}, now())")
	public Integer insertStageInfo(StageInfoEntity stageInfoEntity);
	
	/**
	 * 查询流程引擎stage_info表
	 */
	//@Select("SELECT * FROM `stage_info` WHERE flowInsId = #{flowInsId} ORDER BY DESC LIMIT 1")
	public StageInfoEntity selectStageInfo(String flowInsId);
	
}
