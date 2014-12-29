package com.suning.app.mwwf.spring;

import com.suning.app.mwwf.core.FlowManager;
import com.suning.app.mwwf.core.RouterManager;
import com.suning.app.mwwf.core.impl.WfEngineImpl;

public class SpringWfEngine extends WfEngineImpl{
	

	static {
		engineStart();
	}

	protected static void engineStart() {
		FlowManager.init();
		RouterManager.init();
	}

}
