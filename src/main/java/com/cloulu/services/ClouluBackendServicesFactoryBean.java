package com.cloulu.services;

import java.util.Properties;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;

public class ClouluBackendServicesFactoryBean implements
		FactoryBean<Properties> {

	private BackendServices backendServices;

	public Properties getObject() throws Exception {
		if (backendServices == null) {
			backendServices = new ClouluBackendServices();
		}
		return backendServices.getBackendServicesProperties();
	}

	public Class<?> getObjectType() {
		return Properties.class;
	}

	public boolean isSingleton() {
		return true;
	}

}
