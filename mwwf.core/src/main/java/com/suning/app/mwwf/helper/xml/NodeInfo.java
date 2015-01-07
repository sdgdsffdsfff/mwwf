package com.suning.app.mwwf.helper.xml;

/**
 * xml的树结构实现
 * 
 * @author 14061798 2015年1月7日 下午6:59:52
 */
public class NodeInfo<T> {

	/* 当前dom名称 */
	private String name;

	/* 当前父dom名称 */
	private String parentName;

	/* 当前dom的属性 */
	private T nodeInfo;

	/**
	 * 构造函数
	 * 
	 * @param name 节点
	 */
	public NodeInfo(String name) {
		this.name = name;
	}

	/**
	 * 构造函数
	 * 
	 * @param name 节点
	 * @param parentName 父节点
	 */
	public NodeInfo(String name, String parentName) {
		this.name = name;
		this.parentName = parentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public T getNodeInfo() {
		return nodeInfo;
	}

	public void setNodeInfo(T nodeInfo) {
		this.nodeInfo = nodeInfo;
	}
}
