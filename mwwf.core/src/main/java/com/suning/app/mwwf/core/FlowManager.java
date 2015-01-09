package com.suning.app.mwwf.core;

import java.util.concurrent.CopyOnWriteArrayList;

import com.suning.app.mwwf.helper.StreamHelper;
import com.suning.app.mwwf.helper.xml.NodeInfo;

public class FlowManager {

	private static CopyOnWriteArrayList<NodeInfo<?>> flowXmlEnity = new CopyOnWriteArrayList<NodeInfo<?>>();

	static {
		init();
	}
	
	public static CopyOnWriteArrayList<NodeInfo<?>> getFlowXmlEnity() {
		return flowXmlEnity;
	}

	public static void init() {
		StreamHelper.parseFlowXml("flows.xml");
	}

	public static FlowManager getFlowManagerIns() {
		return new FlowManager();
	}
}
