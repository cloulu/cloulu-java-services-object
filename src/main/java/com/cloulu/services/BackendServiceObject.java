package com.cloulu.services;

import java.util.Map;

public class BackendServiceObject {

	private final String host = "host";
	private final String port = "port";
	private final String username = "username";
	private final String password = "password";
	private final String name = "name";
	
	
	private Map<String,String> backEndServiceInfoMap;
	

	public BackendServiceObject(Map<String, String> backEndServiceInfoMap) {
		super();
		this.backEndServiceInfoMap = backEndServiceInfoMap;
	}
	
	public String[] getKeys(){
		return backEndServiceInfoMap.keySet().toArray(new String[backEndServiceInfoMap.size()]);
	}

	public String getInfo(String key) {
		return backEndServiceInfoMap.get(key);
	}

	public String getHost() {
		return backEndServiceInfoMap.get(host);
	}

	public String getPort() {
		return backEndServiceInfoMap.get(port);
	}

	public String getUsername() {
		return backEndServiceInfoMap.get(username);
	}

	public String getPassword() {
		return backEndServiceInfoMap.get(password);
	}
	
	public String getDBName() {
		return backEndServiceInfoMap.get(name);
	}

}
