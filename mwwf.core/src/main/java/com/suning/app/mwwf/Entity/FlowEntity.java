package com.suning.app.mwwf.Entity;

import java.util.List;

import com.suning.app.mwwf.model.BizDataModel;
import com.suning.app.mwwf.model.EndModel;
import com.suning.app.mwwf.model.StageModel;
import com.suning.app.mwwf.model.StartModel;

public class FlowEntity<T> {
	
	private StartModel startModel;
	private EndModel endModel;
	private List<StageModel> stageModelList;
	private BizDataModel<T> bizDataModel;
	
	public StartModel getStartModel() {
		return startModel;
	}
	public void setStartModel(StartModel startModel) {
		this.startModel = startModel;
	}
	public EndModel getEndModel() {
		return endModel;
	}
	public void setEndModel(EndModel endModel) {
		this.endModel = endModel;
	}
	public List<StageModel> getStageModelList() {
		return stageModelList;
	}
	public void setStageModelList(List<StageModel> stageModelList) {
		this.stageModelList = stageModelList;
	}
	public BizDataModel<T> getBizDataModel() {
		return bizDataModel;
	}
	public void setBizDataModel(BizDataModel<T> bizDataModel) {
		this.bizDataModel = bizDataModel;
	}
	
}
