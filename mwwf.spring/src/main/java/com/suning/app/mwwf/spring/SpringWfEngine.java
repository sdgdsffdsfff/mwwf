package com.suning.app.mwwf.spring;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.suning.app.mwwf.core.FlowManager;
import com.suning.app.mwwf.core.RouterManager;
import com.suning.app.mwwf.core.impl.WfEngineImpl;
import com.suning.app.mwwf.model.BizDataModel;

public class SpringWfEngine extends WfEngineImpl {

	private static final Logger logger = LoggerFactory.getLogger(WfEngineImpl.class);
	
	@Autowired
	StageInfoDaoImpl stageInfoDaoImpl;

	public void engineStart() {
		FlowManager.init();
		RouterManager.init();
	}

	@Override
	public boolean startFlowInstance(String flowInstanceId, String flowInstanceName) {
		if (StringUtils.isBlank(flowInstanceId) || StringUtils.isBlank(flowInstanceName)) {
			logger.error("启动流程失败,流程id:{},流程名称:{}", flowInstanceId, flowInstanceName);
			return false;
		}
		return false;
	}

	@Override
	public boolean triggerByInsId(String flowInstanceId, BizDataModel<?> dataModel) {
		// TODO Auto-generated method stub
		return false;
	}
}
