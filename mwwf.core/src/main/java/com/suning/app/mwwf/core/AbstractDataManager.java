package com.suning.app.mwwf.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suning.app.mwwf.model.BizDataModel;

/**
 * 数据管理中心,有以下功能
 * 
 * <pre>
 * 		1.存放业务模型
 * 		2.存储，获取工作流数据
 * </pre>
 * 
 * @author 14061798 2014年12月27日 下午4:44:31
 */
public abstract class AbstractDataManager {

	private static final Logger logger = LoggerFactory.getLogger(AbstractDataManager.class);

	/* 存放模型的容器 */
	private static Map<String, BizDataModel<?>> bizDataContainer =
			new ConcurrentHashMap<String, BizDataModel<?>>();

	/* 创建stage_info表 */
	protected final static String CTEATE_STAGE_TABLE_SQL = "CreatStageInfoTable.sql";

	/**
	 * 向bizDataContainer注册事件模型
	 * 
	 * @param bizDataModel 业务模型
	 */
	public static void register(BizDataModel<?> bizDataModel) {

		if (bizDataContainer.containsKey(bizDataModel.getName())) {
			logger.warn("模型已经存在：" + bizDataModel.getName());
		} else {
			bizDataContainer.put(bizDataModel.getName(), bizDataModel);
		}
	}

	/**
	 * 根据业务模型名获取模型
	 * 
	 * @param bizDataModelName 业务模型名
	 * @return 业务模型
	 */
	public static BizDataModel<?> getConditionData(String bizDataModelName) {

		if (StringUtils.isBlank(bizDataModelName)) {
			logger.error("模型名称为空");
			return null;
		}
		return bizDataContainer.get(bizDataModelName);
	}
	
	/**
	 * 初始化流程引擎stage_info表
	 */
	public abstract void creatStageInfoTable();
	
	/**
	 * 更新流程引擎stage_info表
	 */
	public abstract int updateStageInfo();

	/**
	 * 插入流程引擎stage_info表
	 */
	public abstract int insertStageInfo();

}
