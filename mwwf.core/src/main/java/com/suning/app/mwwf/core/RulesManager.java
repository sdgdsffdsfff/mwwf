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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suning.app.mwwf.bean.RouterInfoBean;
import com.suning.app.mwwf.bean.RuleBean;
import com.suning.app.mwwf.helper.StreamHelper;
import com.suning.app.mwwf.helper.VelocityHelper;

/**
 * 类RulesManager.java的实现描述：TODO 类实现描述 
 * @author 14061798 2015年1月12日 下午9:55:03
 */
/**
 * 类RulesManager.java的实现描述：TODO 类实现描述
 * 
 * @author 14061798 2015年1月12日 下午9:55:39
 */
public class RulesManager {

	private static final Logger logger = LoggerFactory.getLogger(DataManager.class);

	/* 规则引擎的properties文件 */
	private static final String RULE_EXPRESSION_FILE = "rules.properties";

	/* 业务数据名的分隔符 */
	private static final String CD_SEPARATOR = ",";

	/* 规则表达式 */
	private static final String EXPRESSION_START_MARK = "expression=";

	/* 规则表达式的结束符 */
	private static final String EXPRESSION_END_MARK = "#end";

	/* 业务数据名key */
	private static final String CONDITION_DATAS_START_KEY = "conditionDatas=";
	
	/* 业务数据名结束符 */
	private static final String CONDITION_DATAS_END_KEY = "}";

	/* 单例的线程安全的map,存储别解析完的规则表达式*/
	private static Map<String, RuleBean> rules = new ConcurrentHashMap<String, RuleBean>();

	static {
		// 类初始化时,初始化
		init();
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
			logger.error("failed to parse the expression properties file. [path="
					+ RULE_EXPRESSION_FILE + "]", e);
		} catch (IOException e) {
			logger.error("failed to parse the expression properties file. [path="
					+ RULE_EXPRESSION_FILE + "]", e);
		}
	}

	public static boolean parseRule(RouterInfoBean routerInfo, String flowInsId) {

		if (null == routerInfo || StringUtils.isBlank(flowInsId)) {
			return false;
		}

		// 获取路由名
		String ruleName = routerInfo.getEventName();

		RuleBean ruleObj = RulesManager.getConditionRuleByName(ruleName);

		if (ruleObj == null) {
			logger.error("未获取到规则表达式,规则名:{}", ruleName);
			return false;
		}

		List<BizDataAbstract<?>> bizDatas =
				DataManager.getConditionData(ruleObj.getConditionDataNames());
		if (CollectionUtils.isEmpty(bizDatas)) {
			logger.error("未获取到规则表达式,规则名:{}", ruleName);
			return false;
		}
		return VelocityHelper.validate(bizDatas, ruleObj.getExpression(), flowInsId);
	}

	public static RulesManager getRouterManagerIns() {
		return new RulesManager();
	}
}
