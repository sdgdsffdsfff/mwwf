package com.suning.app.mwwf.model;

public abstract class BizDataModel<T> {
	
    /**
     * 通过该方法取到业务数据
     *
     * @return T 不同的条件会有不同的条件数据返回
     */
    public abstract T get(String flowInstanceId);
    
}
