package com.suning.app.mwwf.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suning.app.mwwf.exception.WfEngineException;

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
public class DataManager {

	private static final Logger logger = LoggerFactory.getLogger(DataManager.class);

	/* 存放模型的容器 */
	private static Map<String, BizDataAbstract<?>> bizDataContainer =
			new ConcurrentHashMap<String, BizDataAbstract<?>>();

	/**
	 * 向bizDataContainer注册事件模型
	 * 
	 * @param bizDataModel 业务模型
	 */
	public static void register(BizDataAbstract<?> bizDataModel) {

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
	public static List<BizDataAbstract<?>> getConditionData(List<String> bizDataModelName) {

		if (CollectionUtils.isEmpty(bizDataModelName)) {
			logger.error("模型名称为空");
			return null;
		}
		
		List<BizDataAbstract<?>> bizData = new ArrayList<BizDataAbstract<?>>();
		
		for (String dataName : bizDataModelName) {
			BizDataAbstract<?> bizDataItem = bizDataContainer.get(dataName);
			if(bizDataItem == null) {
				throw new WfEngineException("不存在业务数据,业务名为：" + dataName);
			}
			bizData.add(bizDataItem);
		}
		
		return bizData;
	}
}
