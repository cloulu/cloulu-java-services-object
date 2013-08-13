package com.cloulu.services;

import java.util.List;

public interface BackEndServices {
	public void initBackEndServices(String servicesInfo);
	public BackEndServiceObject getBackEndService(String serviceName);
	public List<String> getServiceNamesList();
}
