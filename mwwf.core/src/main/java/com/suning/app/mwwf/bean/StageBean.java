package com.suning.app.mwwf.bean;

import java.util.List;
import java.util.Map;

public class StageBean extends BaseBean {

	private Map<String,List<RouterBean>> listRouter;

	public Map<String,List<RouterBean>> getListRouter() {
		return listRouter;
	}

	public void setListRouter(Map<String,List<RouterBean>> listRouter) {
		this.listRouter = listRouter;
	}

}
