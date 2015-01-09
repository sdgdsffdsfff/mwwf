package com.suning.app.mwwf.spring;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.suning.app.mwwf.bean.StageBean;
import com.suning.app.mwwf.constant.Constant;
import com.suning.app.mwwf.core.FlowManager;
import com.suning.app.mwwf.core.RouterManager;
import com.suning.app.mwwf.core.impl.AbstractWfEngine;
import com.suning.app.mwwf.entity.StageInfoEntity;
import com.suning.app.mwwf.enums.StageStatusEnum;

public class SpringWfEngine extends AbstractWfEngine {

	private static final Logger logger = LoggerFactory.getLogger(AbstractWfEngine.class);
	static {
		engineStart();
	}
	@Autowired
	StageInfoDaoImpl stageInfoDaoImpl;

	public static void engineStart() {
		FlowManager.init();
		RouterManager.init();
	}

	@Override
	public boolean startFlowInstance(String flowInstanceId, String flowInstanceName) {

		// 验证连接可用性
		if(stageInfoDaoImpl == null) {
			logger.error("连接数据库失败!");
			return false;
		}
		
		// 参数数验证
		if (StringUtils.isBlank(flowInstanceId) || StringUtils.isBlank(flowInstanceName)) {
			logger.error("启动流程实例失败,流程实例id:{},流程名称:{}", flowInstanceId, flowInstanceName);
			return false;
		}
		
		// 验证是否已经启动了流程实例
		List<StageInfoEntity> stageInfoList = stageInfoDaoImpl.selectStageInfo(flowInstanceId);
		if(stageInfoList != null && !stageInfoList.isEmpty()) {
			logger.error("流程实例已经存在,流程实例id:{}", flowInstanceId);
			return false;
		}
		
		// 插入节点信息
		StageInfoEntity stageInfo = new StageInfoEntity();
		stageInfo.setFlowId(flowInstanceId);
		stageInfo.setFlowName(flowInstanceName);
		stageInfo.setStageName(Constant.START_STAGE);
		stageInfo.setStageStatus(StageStatusEnum.RUNNING.toString());
		Integer affectRows = stageInfoDaoImpl.insertStageInfo(stageInfo);
		if(affectRows != Constant.NUM_1) {
			logger.error("流程实例存储失败,流程实例id:{}", flowInstanceId);
			return false;
		}
		
		logger.info("流程实例成功启动,流程实例id:{}", flowInstanceId);
		return true;
	}

	@Override
	public boolean triggerByInsId(String flowInstanceId, String dataName,boolean isSync) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static StageBean getFirstStageInfo(String flowName) {
		
		
		
		return null;
		
	}
	
	public static StageBean toNextStage(String flowName) {
		return null;
		
	}
	
	public static StageBean getCurrentStageInfo(String flowInstanceId) {
		return null;
		
	}
}
