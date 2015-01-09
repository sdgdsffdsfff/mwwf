package com.suning.app.mwwf.core;

public abstract class BizDataAbstract<T> {

	public BizDataAbstract() {
		DataManager.register(this);
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	abstract T get(T param);
}
