package com.suning.app.mwwf.model;

public abstract class BizDataModel<T> {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	abstract T get(T param);
}
