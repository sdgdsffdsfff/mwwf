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
 * 
 * @author 14061798 2015年1月12日 下午9:55:03
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

	/* 单例的线程安全的map,存储别解析完的规则表达式 */
	private static Map<String, RuleBean> rules = new ConcurrentHashMap<String, RuleBean>();

	/**
	 * 解析规则表达式
	 * 
	 * @param context 从rule.properties获取的字符串,不能为 {@code null}
	 * @return 规则表达式
	 */
	private static String getExpression(String context) {
		int start = context.indexOf(EXPRESSION_START_MARK) + 11;
		int end = context.indexOf(EXPRESSION_END_MARK) + 4;
		return context.substring(start, end).trim();
	}

	/**
	 * 解析业务数据名
	 * 
	 * @param context 从rule.properties获取的字符串
	 * @return 业务数据名列表
	 */
	private static List<String> getConditionDatas(String context) {

		int start = context.indexOf(CONDITION_DATAS_START_KEY) + 15;
		int end = context.indexOf(CONDITION_DATAS_END_KEY);
		String conditionDataNames = context.substring(start, end).trim();
		String[] conditionDataNameArrays = conditionDataNames.split(CD_SEPARATOR);
		return Arrays.asList(conditionDataNameArrays);
	}

	/**
	 * 从容器里获取规则实例
	 * 
	 * @param name 规则名
	 * @return 规则实例
	 */
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
				String context = p.getProperty(String.valueOf(ruleName));
				rule.setExpression(getExpression(context));
				rule.setConditionDataNames(getConditionDatas(context));
				rules.put(String.valueOf(ruleName), rule);
			}
		} catch (FileNotFoundException e) {
			logger.error("未找到资源文件{}", RULE_EXPRESSION_FILE, e);
		} catch (IOException e) {
			logger.error("解析资源文件失败{}", RULE_EXPRESSION_FILE, e);
		}
	}

	/**
	 * 开始解析规则
	 * <ul>
	 * <ol>
	 * 1.根据规则名,获取到已经解析完的规则实例
	 * </ol>
	 * <ol>
	 * 2.根据业务名,获取业务模型
	 * </ol>
	 * <ol>
	 * 3.根据业务模型和规则实例,解析出路由结果
	 * </ol>
	 * </ul>
	 * 
	 * @param routerInfo
	 * @param keyId 业务表和流程引擎表的外键
	 * @return
	 */
	public static boolean parseRule(RouterInfoBean routerInfo, String keyId) {

		if (null == routerInfo || StringUtils.isBlank(keyId)) {
			return false;
		}

		// 获取路由名
		String ruleName = routerInfo.getName();

		// 解析rule.properties,获取ruleName对应的表达式和业务数据名
		RuleBean ruleObj = RulesManager.getConditionRuleByName(ruleName);

		if (ruleObj == null) {
			logger.error("未获取到规则表达式对应的数据,规则名:{}", ruleName);
			return false;
		}

		List<BizDataAbstract<?>> bizDatas =
				DataManager.getConditionData(ruleObj.getConditionDataNames());
		if (CollectionUtils.isEmpty(bizDatas)) {
			logger.error("未获取到规则表达式,规则名:{}", ruleName);
			return false;
		}

		// velocity解析规则
		return VelocityHelper.validate(bizDatas, ruleObj.getExpression(), keyId);
	}

	public static RulesManager getRouterManagerIns() {
		return new RulesManager();
	}
}
