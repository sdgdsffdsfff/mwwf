package com.suning.app.mwwf.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.suning.app.mwwf.bean.RouterInfoBean;
import com.suning.app.mwwf.bean.StageInfoBean;
import com.suning.app.mwwf.helper.StreamHelper;
import com.suning.app.mwwf.helper.xml.DomTree;
import com.suning.app.mwwf.helper.xml.NodeInfo;
import com.suning.app.mwwf.helper.xml.NodePoint;

public class FlowManager {

	private static CopyOnWriteArrayList<NodeInfo<?>> flowXmlEnity = new CopyOnWriteArrayList<NodeInfo<?>>();

	public static void init() {
		StreamHelper.parseFlowXml("flows.xml");
	}

	public static CopyOnWriteArrayList<NodeInfo<?>> getFlowXmlEnity() {
		return flowXmlEnity;
	}

	/**
	 * 根据流程名和节点名，查找路由
	 * 
	 * @param flowName 流程名
	 * @param stageName 节点名
	 * 
	 * @return
	 */
	public static StageInfoBean getRoutersByStageName(String flowName,String stageName) {
		
		NodePoint flowNode = DomTree.getNodeByName(flowName);
		List<NodePoint> child= DomTree.getNodeByName(flowNode,stageName).getChildList();
		List<RouterInfoBean> routers = new ArrayList<RouterInfoBean>();
		for (NodePoint routerInfoBean : child) {
			routers.add((RouterInfoBean)routerInfoBean.getCurrentNodeInfo().getNodeInfo());
        }
		
		StageInfoBean stage = new StageInfoBean();
		stage.setName(stageName);
		stage.setListRouter(routers);
		return stage;
	}
	
	public static FlowManager getFlowManagerIns() {
		return new FlowManager();
	}
}
