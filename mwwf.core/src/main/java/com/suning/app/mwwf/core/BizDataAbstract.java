package com.suning.app.mwwf.core;

import org.springframework.stereotype.Repository;

/**
 * 类BizDataAbstract.java的实现描述：TODO 类实现描述 
 * @author 14061798 2015年1月02日 下午8:10:41
 */
@Repository
public abstract class BizDataAbstract<T> {

	public BizDataAbstract() {
		DataManager.register(this);
	}

	/**
	 * 业务数据名称获取
	 * 
	 * @return 业务数据名
	 */
	public abstract String getName();

	/**
	 * 流程表和业务表的外键
	 * 
	 * @param id 外键
	 * @return 获取业务数据
	 */
	public abstract T get(String id);
}
