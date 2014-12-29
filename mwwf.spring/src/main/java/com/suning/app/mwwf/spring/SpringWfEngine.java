package com.suning.app.mwwf.spring;

import com.suning.app.mwwf.core.AbstractDataManager;
import com.suning.app.mwwf.core.FlowManager;
import com.suning.app.mwwf.core.RouterManager;
import com.suning.app.mwwf.core.impl.WfEngineImpl;

public class SpringWfEngine extends WfEngineImpl{
	
	/* 依赖容器注入 */
	private AbstractDataManager dataManager;
	
	static {
		engineStart();
	}

	protected static void engineStart() {
		FlowManager.init();
		RouterManager.init();
	}

	public AbstractDataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(AbstractDataManager dataManager) {
		this.dataManager = dataManager;
	}

}
