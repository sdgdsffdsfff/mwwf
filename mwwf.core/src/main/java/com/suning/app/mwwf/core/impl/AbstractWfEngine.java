package com.suning.app.mwwf.core.impl;

import com.suning.app.mwwf.core.FlowManager;
import com.suning.app.mwwf.core.RulesManager;

public abstract class AbstractWfEngine {

	private static FlowManager flowManager = FlowManager.getFlowManagerIns();

	private static RulesManager routerManager = RulesManager.getRouterManagerIns();

	public abstract boolean startFlowInstance(String flowInstanceId, String flowName);

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
