package com.suning.app.mwwf.bean;

import java.util.List;

public class StageInfoBean extends BaseBean {

	private String toStage;
	private List<RouterInfoBean> listRouter;

	public List<RouterInfoBean> getListRouter() {
		return listRouter;
	}

	public void setListRouter(List<RouterInfoBean> listRouter) {
		this.listRouter = listRouter;
	}

	public String getToStage() {
		return toStage;
	}

	public void setToStage(String toStage) {
		this.toStage = toStage;
	}
}
