package com.cloulu.services;

import java.util.Properties;

public class ClouluBackendServicesProperties extends Properties{
	
	public ClouluBackendServicesProperties() {
		init(System.getenv("VCAP_SERVICES"));
	}
	
	public ClouluBackendServicesProperties(String servicesInfo) {
		init(servicesInfo);
	}
	
	private void init(String servicesInfo){
		BackendServices backEndServices= new ClouluBackendServices();
		backEndServices.initBackEndServices(servicesInfo);
		System.out.println(servicesInfo);
		for (String serviceName : backEndServices.getServiceNamesList()) {
			BackendServiceObject backEndServiceObject = backEndServices
					.getBackEndService(serviceName);
			for (String key : backEndServiceObject.getKeys()) {
				setProperty(getSystemPropertyKey(serviceName,key), backEndServiceObject.getInfo(key));
				System.out.println("Property Key : "+serviceName+"."+key+" = "+ getProperty(serviceName+"."+key));
			}
		}
	}

	private String getSystemPropertyKey(String serviceName, String key) {
		return serviceName+"."+key;
	}
}
