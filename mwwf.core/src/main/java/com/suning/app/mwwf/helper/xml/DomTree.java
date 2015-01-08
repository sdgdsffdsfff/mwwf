package com.suning.app.mwwf.helper.xml;

import java.util.List;

import com.suning.app.mwwf.bean.FlowBean;
import com.suning.app.mwwf.bean.RouterBean;
import com.suning.app.mwwf.bean.StageBean;
import com.suning.app.mwwf.core.FlowManager;
import com.suning.app.mwwf.helper.PrintHelper;

public class DomTree {

	private static final String ROOT = "root";

	/* 树根 */
	private NodePoint root;

	/**
	 * 构造函数
	 */
	public DomTree() {
		// 初始化domTree生成root根
		root = new NodePoint(new NodeInfo<String>(ROOT));
	}
	
	/**
	 * 打印节点信息
	 * 
	 * @param currentNodeInfo
	 */
	private static void print(NodeInfo<?> currentNodeInfo) {
		if (currentNodeInfo.getNodeInfo() instanceof FlowBean) {
			PrintHelper.print(currentNodeInfo.getName(), 1);
		} else if (currentNodeInfo.getNodeInfo() instanceof RouterBean) {
			PrintHelper.print(currentNodeInfo.getName(), 3);
		} else if (currentNodeInfo.getNodeInfo() instanceof StageBean) {
			PrintHelper.print(currentNodeInfo.getName(), 2);
		} else {
			PrintHelper.print(currentNodeInfo.getName(), 0);
		}
	}
	
	/**
	 * 生成一颗多叉树，根节点为root
	 * 
	 * @param nodeInfoList 生成多叉树的节点集合
	 * @return DomTree 生成dom树
	 */
	public static DomTree createTree(List<NodeInfo<?>> nodeInfoList) {
		if (nodeInfoList == null || nodeInfoList.size() < 0)
			return null;

		DomTree domTree = new DomTree();

		// 将所有节点添加到多叉树中
		for (NodeInfo<?> nodeInfo : nodeInfoList) {

			// 追加每个节点的子孙信息
			if (nodeInfo.getParentName().equals(ROOT)) {
				// 向根添追加子孙信息
				domTree.getRoot().getChildList().add(new NodePoint(nodeInfo));
			} else {
				addChild(domTree.getRoot(), nodeInfo);
			}
		}

		return domTree;
	}

	/**
	 * 向指定多叉树节点添加子节点
	 * 
	 * @param nodePoint 多叉树节点
	 * @param child 节点
	 */
	public static void addChild(NodePoint nodePoint, NodeInfo<?> child) {
		for (NodePoint item : nodePoint.getChildList()) {
			if (item.getCurrentNodeInfo().getName().equals(child.getParentName())) {
				// 找到对应的父亲
				item.getChildList().add(new NodePoint(child));
				break;
			} else {
				if (item.getChildList() != null && item.getChildList().size() > 0) {
					// 递归
					addChild(item, child);
				}
			}
		}
	}

	/**
	 * 根据起始节点全遍历多叉树
	 * 
	 * @param NodePoint 多叉树节点
	 */
	public static void printTree(NodePoint nodePoint) {
		if (nodePoint != null) {
			for (NodePoint index : nodePoint.getChildList()) {
				print(index.getCurrentNodeInfo());
				printTree(index);
			}
		}
	}
	
	/**
	 * 根据节点名遍历多叉树
	 * 
	 * @param NodePoint 多叉树节点
	 * @param nodeName 想要寻找的节点名
	 * @return
	 */
	private static NodePoint iteratorTree(NodePoint nodePoint, String nodeName) {
		if (nodePoint != null) {
			for (NodePoint index : nodePoint.getChildList()) {
				if (nodeName.equals(index.getCurrentNodeInfo().getName())) {
					return index;
				} else {
					return iteratorTree(index, nodeName);
				}
			}
		}
		return null;
	}

	/**
	 * 从根出发，根据节点名获取信息
	 * 
	 * @param nodeName 节点名
	 * @return
	 */
	public static NodePoint getNodeByName(String nodeName) {
		NodePoint rootNode = createTree(FlowManager.getFlowXmlEnity()).getRoot();
		NodePoint result = iteratorTree(rootNode, nodeName);
		return result;
	}
	
	/**
	 * 
	 * 根据起始节点,查询对应的子节点
	 * 
	 * @param rootNode 起始节点
	 * @param nodeName 子节点名
	 * @return
	 */
	public static NodePoint getNodeByName(NodePoint rootNode,String nodeName) {
		NodePoint result = iteratorTree(rootNode, nodeName);
		return result;
	}

	public NodePoint getRoot() {
		return root;
	}

	public void setRoot(NodePoint root) {
		this.root = root;
	}
}
