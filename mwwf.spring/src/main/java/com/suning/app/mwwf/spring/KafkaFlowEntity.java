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

	private Timestamp createTime;

	private Timestamp updateTime;

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
