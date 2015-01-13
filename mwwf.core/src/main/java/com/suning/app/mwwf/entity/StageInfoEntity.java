package com.suning.app.mwwf.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
	private Timestamp createTime;

	/* 流程节点更新时间 */
	private Timestamp updateTime;

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

	public void setFlowInsId(String flowInsId) {
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	@Override
	public String toString() {

		StringBuffer result = new StringBuffer();
		result.append("flowInsId:" + flowInsId).append("\n");
		result.append("flowName:" + flowName).append("\n");
		result.append("stageName:" + stageName).append("\n");
		result.append("stageStatus:" + stageStatus).append("\n");
		result.append("createTime:" + createTime).append("\n");
		result.append("updateTime:" + updateTime).append("\n");
		
		return result.toString();
	}

}
