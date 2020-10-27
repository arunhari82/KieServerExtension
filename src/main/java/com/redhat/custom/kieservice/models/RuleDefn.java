package com.redhat.custom.kieservice.models;

public class RuleDefn {

	private String ruleName;
	private String packageName;
	private String content;
	private String ruleId;

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public RuleDefn(String ruleName, String packageName, String content, String ruleId) {
		super();
		this.ruleName = ruleName;
		this.packageName = packageName;
		this.content = content;
		this.ruleId = ruleId;
	}

	public RuleDefn() {
		super();
		// TODO Auto-generated constructor stub
	}

}
