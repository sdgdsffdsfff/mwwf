package com.suning.app.mwwf.entity;

import java.io.Serializable;

public class StageInfoEntity implements Serializable {

	/* */
	private static final long serialVersionUID = -9167386485948162334L;

	/* 自增id */
	private Integer id;

	/* 流程实例id */
	private String flowInsId;

	/* 流程名 */
	private String flowName;

	/* 流程节点名 */
	private String stageName;

	/* 流程节点状态 1.end 2.running 3.error 4.refused */
	private String stageStatus;

	/* 流程节点创建时间 */
	private String createTime;

	/* 流程节点更新时间 */
	private String updateTime;

	/* 流程节点有效性 1：有效 2.无效 */
	private String del;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFlowInsId() {
		return flowInsId;
	}

	public void setFlowId(String flowInsId) {
		this.flowInsId = flowInsId;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getStageStatus() {
		return stageStatus;
	}

	public void setStageStatus(String stageStatus) {
		this.stageStatus = stageStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

}
