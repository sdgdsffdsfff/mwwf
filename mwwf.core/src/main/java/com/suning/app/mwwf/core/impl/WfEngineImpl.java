package com.suning.app.mwwf.core.impl;

import com.suning.app.mwwf.core.FlowManager;
import com.suning.app.mwwf.core.RouterManager;
import com.suning.app.mwwf.core.WfEngine;
import com.suning.app.mwwf.model.BizDataModel;

public class WfEngineImpl implements WfEngine {

	private static FlowManager flowManager = FlowManager.getFlowManagerIns();

	private static RouterManager routerManager = RouterManager.getRouterManagerIns();

	@Override
	public boolean startFlowByInsId(String flowInstanceId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean startFlowByInsName(String flowInstanceName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean triggerByInsId(String flowInstanceId, BizDataModel<?> dataModel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean triggerByInsName(String flowInstanceName, BizDataModel<?> dataModel) {
		// TODO Auto-generated method stub
		return false;
	}

	public FlowManager flow() {
		return flowManager;
	}

	public RouterManager rule() {
		return routerManager;
	}
}
