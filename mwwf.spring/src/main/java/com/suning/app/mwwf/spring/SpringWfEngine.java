package com.suning.app.mwwf.spring;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.helpers.NullEnumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.suning.app.mwwf.Entity.StageInfoEntity;
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

		// 验证连接可用性
		if(stageInfoDaoImpl == null) {
			logger.error("连接数据库失败!");
			throw new NullPointerException();
		}
		
		// 参数数验证
		if (StringUtils.isBlank(flowInstanceId) || StringUtils.isBlank(flowInstanceName)) {
			logger.error("启动流程实例失败,流程实例id:{},流程名称:{}", flowInstanceId, flowInstanceName);
			return false;
		}
		
		// 验证是否已经启动了流程实例
		List<StageInfoEntity> stageInfoList = stageInfoDaoImpl.selectStageInfo(flowInstanceId);
		if(stageInfoList != null && !stageInfoList.isEmpty()) {
			logger.error("该流程实例已经存在,流程实例id:{}", flowInstanceId);
			return false;
		}
		
		// 插入节点信息
		//Integer affectRows = stageInfoDaoImpl.insertStageInfo(stageInfoEntity);
		
		
		return false;
	}

	@Override
	public boolean triggerByInsId(String flowInstanceId, BizDataModel<?> dataModel) {
		// TODO Auto-generated method stub
		return false;
	}
}
