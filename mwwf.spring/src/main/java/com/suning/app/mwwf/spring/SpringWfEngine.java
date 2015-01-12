package com.suning.app.mwwf.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.suning.app.mwwf.bean.RouterInfoBean;
import com.suning.app.mwwf.bean.RuleBean;
import com.suning.app.mwwf.bean.StageInfoBean;
import com.suning.app.mwwf.constant.Constant;
import com.suning.app.mwwf.core.FlowManager;
import com.suning.app.mwwf.core.RulesManager;
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
		RulesManager.init();
	}

	@Override
	public boolean startFlowInstance(String flowInsId, String flowInsName) {

		// 验证连接可用性
		if (stageInfoDaoImpl == null) {
			logger.error("连接数据库失败!");
			return false;
		}

		// 参数数验证
		if (StringUtils.isBlank(flowInsId) || StringUtils.isBlank(flowInsName)) {
			logger.error("启动流程实例失败,流程实例id:{},流程名称:{}", flowInsId, flowInsName);
			return false;
		}

		// 验证是否已经启动了流程实例
		StageInfoEntity stageInfoList = stageInfoDaoImpl.selectStageInfo(flowInsId);
		if (stageInfoList != null) {
			logger.error("流程实例已经存在,流程实例id:{}", flowInsId);
			return false;
		}

		// 插入节点信息
		StageInfoEntity stageInfo = new StageInfoEntity();
		stageInfo.setFlowId(flowInsId);
		stageInfo.setFlowName(flowInsName);
		stageInfo.setStageName(Constant.START_STAGE);
		stageInfo.setStageStatus(String.valueOf(StageStatusEnum.RUNNING));
		Integer affectRows = stageInfoDaoImpl.insertStageInfo(stageInfo);
		if (affectRows != Constant.NUM_1) {
			logger.error("流程实例存储失败,流程实例id:{}", flowInsId);
			return false;
		}

		logger.info("流程实例成功启动,流程实例id:{}", flowInsId);
		return true;
	}

	@Override
	public boolean triggerByInsId(String flowInsId, String dataName, boolean isSync) {
		// TODO Auto-generated method stub
		return false;
	}

	private void driveStage(String flowInsId, String datalName) {

		if (StringUtils.isBlank(flowInsId) || StringUtils.isBlank(datalName)) {
			logger.error("参数不能为空,flowInsId:{},datalName:{}", flowInsId, datalName);
			return;
		}

		// 获取当前节点信息【数据库】
		StageInfoEntity stageInfo = stageInfoDaoImpl.selectStageInfo(flowInsId);
		if (stageInfo == null) {
			logger.error("未获取到节点信息,流程实例id:{}", flowInsId);
			return;
		}

		// 根据流程名和节点名查询路由信息【配置文件】
		StageInfoBean stage =
				FlowManager
						.getRoutersByStageName(stageInfo.getFlowName(), stageInfo.getStageName());
		if (stage == null) {
			logger.error("查询路由失败,流程名:{},节点名:{}", stageInfo.getFlowName(), stageInfo.getStageName());
			return;
		}

		// 从配置节点信息中获取路由信息【配置文件】
		List<RouterInfoBean> routers = new ArrayList<RouterInfoBean>();
		if (StringUtils.isEmpty(datalName)) {
			// stageRouters = stage.getToStage();
		} else {
			routers = stage.getListRouter();
		}

		if (CollectionUtils.isEmpty(routers)) {
			logger.error("流程的路由不能为空,流程名:{}", stageInfo.getFlowName());
			return;
		}
		
		// 遍历当前节点的所有路由
		for (RouterInfoBean routerInfoBean : routers) {
			RulesManager.parseRule(routerInfoBean, flowInsId);
		}
	}
	public static StageInfoBean toNextStage(String flowName) {
		return null;

	}

	public static StageInfoBean getCurrentStageInfo(String flowInsId) {
		return null;

	}
}
