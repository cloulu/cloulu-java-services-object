package com.cloulu.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClouluBackEndServices implements BackEndServices {

	private Map<String, BackEndServiceObject> backEndServicesMap = new HashMap<String, BackEndServiceObject>();
	private final String serviceKeyString = "\"credentials\":";

	public void initBackEndServices(String servicesInfo) {
		String[] eachServiceTypeInfos = servicesInfo.split("}],");
		for (String sameServicesInfo : eachServiceTypeInfos) {
			sameServicesInfo = sameServicesInfo.substring(sameServicesInfo
					.indexOf("[") + 1);
			String[] serviceInfos = sameServicesInfo.split("},");

			for (String serviceInfo : serviceInfos) {
				String key = getExtractInfo(serviceInfo, "\"name\"");
				String labelString = "label";
				Map<String, String> backEndServiceInfoMap = new HashMap<String, String>();
				backEndServiceInfoMap.put(labelString,
						getExtractInfo(serviceInfo, "\"" + labelString + "\""));
				String serviceCredentialsInfo = moveString(serviceInfo, serviceKeyString);
				backEndServicesMap.put(
						key,
						buildNextBackEnServiceObject(backEndServiceInfoMap,
								serviceCredentialsInfo));
				
			}
		}
	}

	private String moveString(String targetString, String findString) {
		return targetString.substring(targetString.indexOf(findString)
				+ findString.length());
	}

	private BackEndServiceObject buildNextBackEnServiceObject(
			Map<String, String> backEndServiceInfoMap,
			String serviceCredentialsInfo) {
		String[] serviceCredentials = serviceCredentialsInfo.split(",");
		for (String eachInfo : serviceCredentials) {
			eachInfo = eachInfo.replaceAll("[\"\\]{}]", "");
//			System.out.println("eachInfo = " + eachInfo);
			String[] tempStrings = eachInfo.split(":");
			String key = extractRealString(tempStrings[0]);
//			System.out.println("key = " + key);
			String value = extractRealString(tempStrings[1]);
//			System.out.println("value = " + value);
			backEndServiceInfoMap.put(key, value);
		}
		return new BackEndServiceObject(backEndServiceInfoMap);
	}

	private String extractRealString(String string) {
		if (string.contains("\"")) {
			string = string.substring(string.indexOf("\"")+1,
					string.lastIndexOf("\""));
		}
		return string;
	}

	private String getExtractInfo(String serviceInfo, String findString) {
		if (serviceInfo.contains(findString)) {
			serviceInfo = moveString(serviceInfo, findString);
			return extractString(serviceInfo);
		}
		return "";
	}

	private String extractString(String tempString) {
		return tempString.substring(tempString.indexOf(":\"") + 2,
				tempString.indexOf("\","));
	}

	public BackEndServiceObject getBackEndService(String serviceName) {
		return backEndServicesMap.get(serviceName);
	}

	public List<String> getServiceNamesList() {
		return Arrays.asList(backEndServicesMap.keySet().toArray(
				new String[backEndServicesMap.size()]));
	}

}
