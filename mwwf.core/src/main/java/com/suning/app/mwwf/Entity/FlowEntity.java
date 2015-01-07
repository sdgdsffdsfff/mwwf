package com.suning.app.mwwf.Entity;

import java.util.List;
import java.util.Map;

import com.suning.app.mwwf.bean.StageBean;

public class FlowEntity {
	
	private String name;
	private Map<String,List<StageBean>> stageBeanMap;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String,List<StageBean>> getStageBeanMap() {
		return stageBeanMap;
	}
	public void setStageBeanMap(Map<String,List<StageBean>> stageBeanMap) {
		this.stageBeanMap = stageBeanMap;
	}
}
