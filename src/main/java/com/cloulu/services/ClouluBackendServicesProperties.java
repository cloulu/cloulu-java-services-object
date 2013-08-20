package com.cloulu.services;

import java.util.Properties;

public class ClouluBackendServicesProperties extends Properties{
	
	public ClouluBackendServicesProperties() {
		BackEndServices backEndServices= new ClouluBackEndServices();
		backEndServices.initBackEndServices(System.getenv("VCAP_SERVICES"));
		System.out.println(System.getenv("VCAP_SERVICES"));
		for (String serviceName : backEndServices.getServiceNamesList()) {
			BackEndServiceObject backEndServiceObject = backEndServices
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
