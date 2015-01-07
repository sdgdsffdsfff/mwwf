package com.suning.app.mwwf.core;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.suning.app.mwwf.Entity.FlowEntity;
import com.suning.app.mwwf.helper.StreamHelper;

public class FlowManager {

	private static Map<String, List<FlowEntity>> flowEnityMap =
			new ConcurrentHashMap<String, List<FlowEntity>>();

	public static Map<String, List<FlowEntity>> getFlowEnityMap() {
		return flowEnityMap;
	}

	public static void init() {
		StreamHelper.parseFlowXml("flows.xml");
	}

	public static FlowManager getFlowManagerIns() {
		return new FlowManager();
	}
}
