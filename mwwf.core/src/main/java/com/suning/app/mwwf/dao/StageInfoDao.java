package com.suning.app.mwwf.dao;

import java.util.List;

import com.suning.app.mwwf.Entity.StageInfoEntity;

public interface StageInfoDao {

	/**
	 * 更新流程引擎stage_info表
	 */
	public Integer updateStageInfo();

	/**
	 * 插入流程引擎stage_info表
	 */
	public Integer insertStageInfo(StageInfoEntity stageInfoEntity);
	
	/**
	 * 查询流程引擎stage_info表
	 */
	public List<StageInfoEntity> selectStageInfo(String flowInsId);
	
}
