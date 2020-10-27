package com.redhat.custom.kieservice.models;

import java.util.ArrayList;

public class KieServerDefnResponse {

	private ArrayList<ContainerInfo> containers;

	public ArrayList<ContainerInfo> getContainers() {
		return containers;
	}

	public void setContainers(ArrayList<ContainerInfo> containers) {
		this.containers = containers;
	}

	public KieServerDefnResponse(ArrayList<ContainerInfo> containers) {
		super();
		this.containers = containers;
	}

	public KieServerDefnResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
}
