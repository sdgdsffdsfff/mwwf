package com.suning.app.mwwf.bean;

import java.util.List;

public class RuleBean extends BaseBean {

	/* 从rule.properties解析出来的表达式 */
	private String expression;

	/* 从rule.properties解析出来业务数据名 */
	private List<String> conditionDataNames;

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public List<String> getConditionDataNames() {
		return conditionDataNames;
	}

	public void setConditionDataNames(List<String> conditionDataNames) {
		this.conditionDataNames = conditionDataNames;
	}
}
