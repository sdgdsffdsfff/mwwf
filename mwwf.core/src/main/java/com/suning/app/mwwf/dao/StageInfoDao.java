package com.suning.app.mwwf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.suning.app.mwwf.entity.StageInfoEntity;

public interface StageInfoDao {

	/**
	 * 更新流程引擎stage_info表
	 */
	@Update("UPDATE `mwwf`.`stage_info` SET `id`=NULL, `flowId`=NULL, `flowName`=NULL, `stageName`=NULL, `stageStatus`=NULL, `createTime`=NULL, `updateTime`=NULL, `del`=NULL WHERE (ISNULL(`id`));")
	public Integer updateStageInfo();

	/**
	 * 插入流程引擎stage_info表
	 */
	@Insert("INSERT INTO `stage_info` (`flowId`, `flowName`, `stageName`, `stageStatus`, `createTime`) VALUES (#{flowId}, #{flowName}, #{stageName}, #{stageStatus}, now())")
	public Integer insertStageInfo(StageInfoEntity stageInfoEntity);
	
	/**
	 * 查询流程引擎stage_info表
	 */
	@Select("SELECT * FROM `stage_info` WHERE flowId = #{flowId}")
	public List<StageInfoEntity> selectStageInfo(String flowInsId);
	
}
