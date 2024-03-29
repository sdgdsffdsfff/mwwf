package com.suning.app.mwwf.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.suning.app.mwwf.bean.RouterInfoBean;
import com.suning.app.mwwf.bean.StageInfoBean;
import com.suning.app.mwwf.constant.Constant;
import com.suning.app.mwwf.core.FlowManager;
import com.suning.app.mwwf.core.RulesManager;
import com.suning.app.mwwf.core.impl.AbstractWfEngine;
import com.suning.app.mwwf.entity.StageInfoEntity;
import com.suning.app.mwwf.enums.StageStatusEnum;

public class SpringWfEngine extends AbstractWfEngine {

	private static final Logger logger = LoggerFactory.getLogger(SpringWfEngine.class);

	/* 节点结束标识符 */
	private static final String FINISHED = "FINISHED";
	
	/* 节点结束标识符 */
	private static final String END_STAGE = "endStage";

	/* 节点状态:结束 */
	private static final String STAGE_STATUS_END = "END";

	/* 节点状态:运行 */
	private static final String STAGE_STATUS_RUNNING = "RUNNING";

	static {
		engineStart();
	}

	@Autowired
	private StageInfoDaoImpl stageInfoDaoImpl;

	//@Autowired
	//BaseEvent baseEvent;

	public static void engineStart() {
		FlowManager.init();
		RulesManager.init();
	}

	/* (non-Javadoc)
	 * @see com.suning.app.mwwf.core.impl.AbstractWfEngine#startFlowInstance(java.lang.String, java.lang.String)
	 */
	@Override
	public Integer startFlowInstance(String flowInsId, String flowName) {

		try {
			// 验证连接可用性
			if (stageInfoDaoImpl == null) {
				logger.error("连接数据库失败!");
				return 0;
			}

			// 参数数验证
			if (StringUtils.isBlank(flowInsId) || StringUtils.isBlank(flowName)) {
				logger.error("启动流程实例失败,流程实例id:{},流程名称:{}", flowInsId, flowName);
				return Constant.NUM_0;
			}

			// 验证是否已经启动了流程实例
			StageInfoEntity stageInfoList = stageInfoDaoImpl.selectStageInfo(flowInsId);
			if (stageInfoList != null) {
				logger.error("流程实例已经存在,流程实例id:{}", flowInsId);
				return Constant.NUM_0;
			}

			// 插入节点信息
			StageInfoEntity stageInfo = new StageInfoEntity();
			stageInfo.setFlowInsId(flowInsId);
			stageInfo.setFlowName(flowName);
			stageInfo.setStageName(Constant.START_STAGE);
			stageInfo.setStageStatus(String.valueOf(StageStatusEnum.RUNNING));
			Integer bizKey = stageInfoDaoImpl.insertStageInfo(stageInfo);
			if (bizKey == Constant.NUM_0) {
				logger.error("流程实例存储失败,流程实例id:{}", flowInsId);
				return Constant.NUM_0;
			}

			logger.info("流程实例成功启动,流程实例id:{}", flowInsId);
			return bizKey;
		} catch (Exception e) {
			logger.error("流程实例启动失败,流程实例id:{}", flowInsId,e);
		}
		return Constant.NUM_0;
	}

	@Override
	public boolean triggerByInsId(String flowInsId, String dataName, boolean isSync) {
		if (StringUtils.isEmpty(flowInsId) || StringUtils.isEmpty(dataName)) {
			logger.error("触发流程失败,参数不能为空,流程实例id:{},业务名:{}", flowInsId, dataName);
			return false;
		}
		driveStage(flowInsId, dataName, isSync);
		return true;
	}

	private void driveStage(String flowInsId, String datalName, boolean isSync) {

		try {
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

				if (!RulesManager.parseRule(routerInfoBean, flowInsId)) {
					continue;
				}

				// 匹配其中一条路由时,触发事件和
				String eventName = routerInfoBean.getEventName();
				if (!handler(flowInsId, eventName, isSync)) {
					logger.info("触发事件失败,流程实例:{},事件名:{}", flowInsId, eventName);
					continue;
				}

				boolean toNextResult = toNextStage(stageInfo.getFlowName(), routerInfoBean, stageInfo);
				if (toNextResult) {
			        break;
			    }
			}
		} catch (Exception e) {
			logger.error("驱动到下一节点时出错",e);
		}
	}

	private boolean handler(String flowInsId, String eventName, boolean isSync) {
		return true;
		// EventBus.getInstance().post(baseEvent,isSync);
	}

	private boolean toNextStage(String flowName, RouterInfoBean routerBean,
			StageInfoEntity stageInfo) {

		StageInfoEntity nextStageInfo = new StageInfoEntity();
		nextStageInfo.setFlowName(flowName);
		nextStageInfo.setFlowInsId(stageInfo.getFlowInsId());
		nextStageInfo.setStageName(routerBean.getToStage());

		if (FINISHED.equalsIgnoreCase(routerBean.getToStage())) {
			nextStageInfo.setStageStatus(FINISHED);
		} else {
			nextStageInfo.setStageStatus(STAGE_STATUS_RUNNING);
		}
		stageInfo.setStageStatus(STAGE_STATUS_END);

		try {
			stageInfoDaoImpl.updateStageInfo(stageInfo);
			stageInfoDaoImpl.insertStageInfo(nextStageInfo);
			logger.info("successfully drive from: {} to next stage: {}!",stageInfo.getStageName(),nextStageInfo.getStageName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public abstract class BizDataHelper<T> {
		abstract List<T> getBizData(String flowInsId);
	}
	public static List<?> getCurrentStageInfo(BizDataHelper<?> helper,String flowInsId) {
		return helper.getBizData(flowInsId);
	}
}
