package com.suning.app.mwwf.bean;

import java.util.List;

public class RuleBean extends BaseBean {

	private String expression;
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
