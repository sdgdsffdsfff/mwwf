package com.suning.app.mwwf.spring;

import java.sql.Timestamp;

public class KafkaFlowEntity {

	private Integer id;

	private String flowInsId;

	private String systemName;

	private String systemDes;

	private String topic;

	private String partition;

	private String repitition;

	private String applicantId;

	private String isLeader;

	private String approverId;

	private String remark;

	private String action;

	private Timestamp createTime;

	private Timestamp updateTime;

	private String del;

	public Integer getId() {
		return id;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public String getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemDes() {
		return systemDes;
	}

	public void setSystemDes(String systemDes) {
		this.systemDes = systemDes;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getPartition() {
		return partition;
	}

	public void setPartition(String partition) {
		this.partition = partition;
	}

	public String getRepitition() {
		return repitition;
	}

	public void setRepitition(String repitition) {
		this.repitition = repitition;
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

}
