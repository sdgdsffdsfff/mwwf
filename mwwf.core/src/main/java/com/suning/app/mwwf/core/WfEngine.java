package com.suning.app.mwwf.core;

import com.suning.app.mwwf.model.BizDataModel;

public interface WfEngine {

	/**
	 * 根据流程定义ID启动流程实例
	 * 
	 * @param flowInstanceId
	 *            流程ID
	 * @return boolean 是否启动成功
	 * 
	 */
	boolean startFlowByInsId(String flowInstanceId);

	/**
	 * 根据流程名称启动流程实例
	 * 
	 * @param flowInstanceName
	 *            流程名称
	 * @return boolean 是否启动成功
	 * 
	 */
	boolean startFlowByInsName(String flowInstanceName);
	
	/**
	 * 根据流程定义ID启动流程实例
	 * 
	 * @param flowInstanceId
	 *            流程ID
	 *            
	 * @param dataModel
	 *            业务模型
	 * 
	 * @return boolean 是否启动成功
	 * 
	 */
	boolean triggerByInsId(String flowInstanceId, BizDataModel<?> dataModel);
	
	/**
	 * 根据流程名称启动流程实例
	 * 
	 * @param flowInstanceName
	 *            流程名称
	 * @param dataModel
	 *            业务模型
	 * 
	 * @return boolean 是否启动成功
	 * 
	 */
	boolean triggerByInsName(String flowInstanceName, BizDataModel<?> dataModel);
}
