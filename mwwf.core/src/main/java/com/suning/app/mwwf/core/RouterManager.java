package com.suning.app.mwwf.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suning.app.mwwf.bean.RuleBean;
import com.suning.app.mwwf.helper.StreamHelper;

public class RouterManager {

	private static final Logger logger = LoggerFactory.getLogger(DataManager.class);

	private static final String RULE_EXPRESSION_FILE = "rules.properties";
	private static final String CD_SEPARATOR = ",";
	private static final String EXPRESSION_START_MARK = "expression=";
	private static final String EXPRESSION_END_MARK = "#end";
	private static final String CONDITION_DATAS_START_KEY = "conditionDatas=";
	private static final String CONDITION_DATAS_END_KEY = "}";

	private static Map<String, RuleBean> rules = new ConcurrentHashMap<String, RuleBean>();
	private static VelocityEngine velocityEngine = new VelocityEngine();

	static {
		init();
	}

	public static RuleBean getConditionRuleByName(String name) {

		if (StringUtils.isEmpty(name) || !rules.containsKey(name)) {
			return null;
		}
		return rules.get(name);
	}

	public static void init() {
		InputStream in;
		try {
			in = StreamHelper.getStreamFromClasspath(RULE_EXPRESSION_FILE);
			Properties p = new Properties();
			p.load(in);
			Enumeration<Object> e = p.keys();
			while (e.hasMoreElements()) {
				Object ruleName = e.nextElement();
				RuleBean rule = new RuleBean();
				rule.setName(ruleName.toString());
				String context = p.getProperty(ruleName.toString());
				rule.setExpression(getExpression(context));
				rule.setConditionDataNames(getConditionDatas(context));
				rules.put(ruleName.toString(), rule);
			}
		} catch (FileNotFoundException e) {
			logger.error("failed to parse the expression properties file. [path=" + RULE_EXPRESSION_FILE + "]", e);
		} catch (IOException e) {
			logger.error("failed to parse the expression properties file. [path=" + RULE_EXPRESSION_FILE + "]", e);
		}
	}

	private static String getExpression(String context) {
		int start = context.indexOf(EXPRESSION_START_MARK) + 11;
		int end = context.indexOf(EXPRESSION_END_MARK) + 4;
		return context.substring(start, end).trim();
	}

	private static List<String> getConditionDatas(String context) {

		int start = context.indexOf(CONDITION_DATAS_START_KEY) + 15;
		int end = context.indexOf(CONDITION_DATAS_END_KEY);
		String conditionDataNames = context.substring(start, end).trim();
		String[] conditionDataNameArrays = conditionDataNames.split(CD_SEPARATOR);
		return Arrays.asList(conditionDataNameArrays);
	}

	public static RouterManager getRouterManagerIns() {
		return new RouterManager();
	}
}
