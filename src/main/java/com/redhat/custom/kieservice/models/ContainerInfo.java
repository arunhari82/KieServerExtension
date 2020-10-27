package com.redhat.custom.kieservice.models;

import java.util.List;

public class ContainerInfo {

	private String containerName;
	private List<RuleDefn> rules;
	private String sourcePath;

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

	public List<RuleDefn> getRules() {
		return rules;
	}

	public void setRules(List<RuleDefn> rules) {
		this.rules = rules;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public ContainerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContainerInfo(String containerName, List<RuleDefn> rules, String sourcePath) {
		super();
		this.containerName = containerName;
		this.rules = rules;
		this.sourcePath = sourcePath;
	}
}
