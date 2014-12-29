package com.suning.app.mwwf.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RouterManager {

	private static Map<String, String> rules = new ConcurrentHashMap<String, String>();

	public static Map<String, String> getRules() {
		return rules;
	}

	public static void init() {
		
	}

	public static RouterManager getRouterManagerIns() {
		return new RouterManager();
	}
}
