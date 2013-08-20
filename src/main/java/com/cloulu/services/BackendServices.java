package com.cloulu.services;

import java.util.List;
import java.util.Properties;

public interface BackendServices {
	public void initBackEndServices(String servicesInfo);
	public BackendServiceObject getBackEndService(String serviceName);
	public List<String> getServiceNamesList();
}
