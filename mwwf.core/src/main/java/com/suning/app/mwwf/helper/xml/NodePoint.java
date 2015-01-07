package com.suning.app.mwwf.helper.xml;

import java.util.ArrayList;
import java.util.List;

public class NodePoint {

	/* 树节点 */
	private NodeInfo<?> currentNodeInfo;

	/* 指向的树集合 */
	private List<NodePoint> childList;

	/**
	 * 构造函数
	 * 
	 * @param data 树节点
	 */
	public NodePoint(NodeInfo<?> currentNodeInfo) {
		this.currentNodeInfo = currentNodeInfo;
		this.childList = new ArrayList<NodePoint>();
	}

	/**
	 * 构造函数
	 * 
	 * @param data 树节点
	 * @param childList 子树集合
	 */
	public NodePoint(NodeInfo<?> currentNodeInfo, List<NodePoint> childList) {
		this.currentNodeInfo = currentNodeInfo;
		this.childList = childList;
	}

	public NodeInfo<?> getCurrentNodeInfo() {
		return currentNodeInfo;
	}

	public void setData(NodeInfo<?> currentNodeInfo) {
		this.currentNodeInfo = currentNodeInfo;
	}

	public List<NodePoint> getChildList() {
		return childList;
	}

	public void setChildList(List<NodePoint> childList) {
		this.childList = childList;
	}
}
