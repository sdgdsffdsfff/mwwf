package com.suning.app.mwwf.core.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suning.app.mwwf.core.FlowManager;
import com.suning.app.mwwf.core.RouterManager;
import com.suning.app.mwwf.core.WfEngine;
import com.suning.app.mwwf.model.BizDataModel;

public abstract class WfEngineImpl {

	private static FlowManager flowManager = FlowManager.getFlowManagerIns();

	private static RouterManager routerManager = RouterManager.getRouterManagerIns();

	public abstract boolean startFlowInstance(String flowInstanceId, String flowInstanceName);

	public abstract boolean triggerByInsId(String flowInstanceId, BizDataModel<?> dataModel);

	public FlowManager flow() {
		return flowManager;
	}

	public RouterManager rule() {
		return routerManager;
	}
}
