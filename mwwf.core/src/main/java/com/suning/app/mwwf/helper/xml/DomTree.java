package com.suning.app.mwwf.helper.xml;

import java.util.ArrayList;
import java.util.List;

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
	 * 生成一颗多叉树，根节点为root
	 * 
	 * @param nodeInfoList 生成多叉树的节点集合
	 * @return DomTree 生成dom树
	 */
	public DomTree createTree(List<NodeInfo<?>> nodeInfoList) {
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
	public void addChild(NodePoint nodePoint, NodeInfo<?> child) {
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
	 * 遍历多叉树
	 * 
	 * @param manyTreeNode 多叉树节点
	 * @return
	 */
	public String iteratorTree(NodePoint nodePoint) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("\n");

		if (nodePoint != null) {
			for (NodePoint index : nodePoint.getChildList()) {
				buffer.append(index.getCurrentNodeInfo().getName() + ",");

				if (index.getChildList() != null && index.getChildList().size() > 0) {
					buffer.append(iteratorTree(index));
				}
			}
		}

		buffer.append("\n");

		return buffer.toString();
	}


	public NodePoint getRoot() {
		return root;
	}

	public void setRoot(NodePoint root) {
		this.root = root;
	}

	public static void main(String[] args) {
		List<NodeInfo<?>> treeNodes = new ArrayList<NodeInfo<?>>();
		treeNodes.add(new NodeInfo<String>("kafka", "root"));
		treeNodes.add(new NodeInfo<String>("firstStage", "kafka"));
		treeNodes.add(new NodeInfo<String>("routerA", "firstStage"));
		treeNodes.add(new NodeInfo<String>("endStage", "kafka"));
		treeNodes.add(new NodeInfo<String>("zookeeper", "root"));
		treeNodes.add(new NodeInfo<String>("firstStage", "zookeeper"));
		treeNodes.add(new NodeInfo<String>("endStage", "zookeeper"));
		treeNodes.add(new NodeInfo<String>("routerB", "endStage"));

		DomTree tree = new DomTree();

		System.out.println(tree.iteratorTree(tree.createTree(treeNodes).getRoot()));
	}
	
}
