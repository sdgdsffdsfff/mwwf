package com.suning.app.mwwf.core;

import com.suning.app.mwwf.model.BizDataModel;

public interface Engine<T> {

	/**
	 * 根据流程定义ID启动流程实例
	 * 
	 * @param flowId
	 *            流程ID
	 * @return boolean 是否启动成功
	 * 
	 */
	boolean startFlowById(String flowInstanceId);

	/**
	 * 根据流程名称启动流程实例
	 * 
	 * @param flowName
	 *            流程名称
	 * @return boolean 是否启动成功
	 * 
	 */
	boolean startFlowByName(String flowInstanceIdName);

	/**
	 * 根据流程名称启动流程实例
	 * 
	 * @param <T>
	 * 
	 * @param flowName
	 *            流程名称
	 * @param dataModel
	 *            业务模型
	 * 
	 * @return boolean 是否启动成功
	 * 
	 */
	boolean triggerByCondition(String flowName, BizDataModel<T> dataModel);
}
