package com.suning.app.mwwf.core.impl;

import com.suning.app.mwwf.core.FlowManager;
import com.suning.app.mwwf.core.RulesManager;

public abstract class AbstractWfEngine {

	private static FlowManager flowManager = FlowManager.getFlowManagerIns();

	private static RulesManager routerManager = RulesManager.getRouterManagerIns();

	/**
	 * 第一次创建节点时,生成整套流程实例唯一id,用来识别某次申请流程
	 * 
	 * @param flowInsId 流程实例唯一id
	 * @param flowName 流程名
	 * @return 如果成功启动实例则返回{@code true},否则返回{@code false}
	 */
	public abstract Integer startFlowInstance(String flowInsId, String flowName);

	/**
	 * <p>
	 * 触发流程向下走
	 * <li>1.找到当前节点相关的信息</li>
	 * <li>2.判断</li>
	 * <li>3.满足的话会生成相应的事件</li>
	 * <li>4.触发事件</li>
	 * <li>5.流程节点的驱动</li>
	 * </p>
	 * 
	 * @param flowInstanceId
	 * @param dataName
	 * @param isSync 是否同步
	 * @return
	 */
	public abstract boolean triggerByInsId(String flowInstanceId, String dataName, boolean isSync);

	public FlowManager flow() {
		return flowManager;
	}

	public RulesManager rule() {
		return routerManager;
	}
}
