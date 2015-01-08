package com.suning.app.mwwf.core.impl;

import com.suning.app.mwwf.core.FlowManager;
import com.suning.app.mwwf.core.RouterManager;

public abstract class WfEngineImpl {

	private static FlowManager flowManager = FlowManager.getFlowManagerIns();

	private static RouterManager routerManager = RouterManager.getRouterManagerIns();

	public abstract boolean startFlowInstance(String flowInstanceId, String flowInstanceName);

	/**
     * <p>
     * 触发流程向下走
     * <li>1.找到当前节点相关的{@link ConditionRule}</li>
     * <li>2.判断相关的{@link ConditionRule}是否满足</li>
     * <li>3.满足的话会生成相应的{@link Event}</li>
     * <li>4.触发{@link EventBus}.post(event)</li>
     * <li>5.流程节点的驱动</li>
     * </p>
     * 
	 * @param flowInstanceId
	 * @param dataName 
	 * @return
	 */
	public abstract boolean triggerByInsId(String flowInstanceId, String dataNameKey);

	public FlowManager flow() {
		return flowManager;
	}

	public RouterManager rule() {
		return routerManager;
	}
}
