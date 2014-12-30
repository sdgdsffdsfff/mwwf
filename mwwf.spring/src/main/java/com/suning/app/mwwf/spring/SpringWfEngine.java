package com.suning.app.mwwf.spring;

import com.suning.app.mwwf.core.FlowManager;
import com.suning.app.mwwf.core.RouterManager;
import com.suning.app.mwwf.core.impl.WfEngineImpl;

public class SpringWfEngine extends WfEngineImpl{

	private DataManagerImpl dataManagerImpl;
	
	public DataManagerImpl getDataManagerImpl() {
		return dataManagerImpl;
	}

	public void setDataManagerImpl(DataManagerImpl dataManagerImpl) {
		this.dataManagerImpl = dataManagerImpl;
	}
	
	public void engineStart() {
		FlowManager.init();
		RouterManager.init();
		dataManagerImpl.init();
	}
}
