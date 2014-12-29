package com.suning.app.mwwf.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.suning.app.mwwf.Entity.FlowEntity;

public class FlowManager {

	private static Map<String, FlowEntity<?>> flowEnityList =
			new ConcurrentHashMap<String, FlowEntity<?>>();

	public static Map<String, FlowEntity<?>> getFlowEnityList() {
		return flowEnityList;
	}

	public static void init() {
		System.out.println("");
	}

	public static FlowManager getFlowManagerIns() {
		return new FlowManager();
	}
}
