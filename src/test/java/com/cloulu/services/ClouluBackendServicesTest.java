package com.cloulu.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class ClouluBackendServicesTest {

	BackendServices backEndServices;

	@Before
	public void setUp() throws Exception {
		String servicesInfo = "{\"postgresql-9.0\":[{\"name\":\"mypostgresqldb\",\"label\":\"postgresql-9.0\",\"plan\":\"free\",\"tags\":[\"relational\",\"postgresql-9.0\",\"postgresql\"],\"credentials\":{\"name\":\"dfc05d87f53d0402d918e55d61c1b0746\",\"host\":\"192.168.9.243\",\"hostname\":\"192.168.9.243\",\"port\":5433,\"user\":\"u5d16921ca6bb43ef9f5fafdcfb36a400\",\"username\":\"u5d16921ca6bb43ef9f5fafdcfb36a400\",\"password\":\"p04325c580f21442da2517e241ef89c2e\"}},{\"name\":\"postgresql2\",\"label\":\"postgresql-9.0\",\"plan\":\"free\",\"tags\":[\"relational\",\"postgresql-9.0\",\"postgresql\"],\"credentials\":{\"name\":\"d222a5e2f34744dc997db60fd9f7fa441\",\"host\":\"192.168.9.243\",\"hostname\":\"192.168.9.243\",\"port\":5433,\"user\":\"ua47998aa77bb4754af92675f73b4bb63\",\"username\":\"ua47998aa77bb4754af92675f73b4bb63\",\"password\":\"p5ed26195080b467f83a69f7bc5a059a2\"}}],\"mysql-5.1\":[{\"name\":\"mysql1\",\"label\":\"mysql-5.1\",\"plan\":\"free\",\"tags\":[\"relational\",\"mysql-5.1\",\"mysql\"],\"credentials\":{\"name\":\"d522c279d846d4ab585d4bcbe5c1a5f3e\",\"hostname\":\"192.168.5.242\",\"host\":\"192.168.5.242\",\"port\":3306,\"user\":\"uQ6h6t1Ni5JnX\",\"username\":\"uQ6h6t1Ni5JnX\",\"password\":\"pk8bOqIxPH33C\"}},{\"name\":\"mysql2\",\"label\":\"mysql-5.1\",\"plan\":\"free\",\"tags\":[\"relational\",\"mysql-5.1\",\"mysql\"],\"credentials\":{\"name\":\"dd2dcd5b60e2c4fd1a1561598ea1f5da4\",\"hostname\":\"192.168.9.242\",\"host\":\"192.168.9.242\",\"port\":3306,\"user\":\"uU78ysAgEf6W6\",\"username\":\"uU78ysAgEf6W6\",\"password\":\"pMWmqdDkyAeZr\"}}],\"rabbitmq-2.4\":[{\"name\":\"rabbitmq1\",\"label\":\"rabbitmq-2.4\",\"plan\":\"free\",\"tags\":[\"message-queue\",\"amqp\",\"rabbitmq-2.4\",\"rabbitmq\"],\"credentials\":{\"name\":\"feea3f02-3fe7-4189-9153-05a72f0f9164\",\"hostname\":\"192.168.9.245\",\"host\":\"192.168.9.245\",\"port\":10002,\"vhost\":\"va4e1e7a71c094ba58e135f7b32b1aa83\",\"username\":\"uK1EMzvqCXz0t\",\"user\":\"uK1EMzvqCXz0t\",\"password\":\"p0F6wZacx6fc2\",\"pass\":\"p0F6wZacx6fc2\",\"url\":\"amqp://uK1EMzvqCXz0t:p0F6wZacx6fc2@192.168.9.245:10002/va4e1e7a71c094ba58e135f7b32b1aa83\"}}],\"redis-2.6\":[{\"name\":\"redis1\",\"label\":\"redis-2.6\",\"plan\":\"free\",\"tags\":[\"key-value\",\"nosql\",\"redis-2.6\",\"redis\"],\"credentials\":{\"hostname\":\"192.168.9.246\",\"host\":\"192.168.9.246\",\"port\":5004,\"password\":\"15a7771e-da18-4ec2-9836-2551c581e38b\",\"name\":\"e801ed2f-2515-4416-854b-463e9596c591\"}}],\"mongodb-1.8\":[{\"name\":\"mongodb1\",\"label\":\"mongodb-1.8\",\"plan\":\"free\",\"tags\":[\"nosql\",\"document\",\"mongodb-1.8\",\"mongodb\"],\"credentials\":{\"hostname\":\"192.168.9.244\",\"host\":\"192.168.9.244\",\"port\":25006,\"username\":\"ef262a3a-f442-4fad-940a-29696f77276c\",\"password\":\"133a143f-8dfa-44a3-8c60-46bc37c50fe9\",\"name\":\"57b52c75-5815-42b2-80ed-af850ce1a1d3\",\"db\":\"db\",\"url\":\"mongodb://ef262a3a-f442-4fad-940a-29696f77276c:133a143f-8dfa-44a3-8c60-46bc37c50fe9@192.168.9.244:25006/db\"}}]}";
		backEndServices = new ClouluBackendServices(servicesInfo);
	}

	@Test
	public final void testGetInfo() {
		BackendServiceObject beso = backEndServices
				.getBackEndService("mypostgresqldb");
		assertEquals(beso.getInfo("name"), "dfc05d87f53d0402d918e55d61c1b0746");
		assertEquals(beso.getDBName(), "dfc05d87f53d0402d918e55d61c1b0746");
		assertEquals(beso.getInfo("label"), "postgresql-9.0");
		assertEquals(beso.getHost(), "192.168.9.243");
		assertEquals(beso.getPort(), "5433");
		assertEquals(beso.getUsername(), "u5d16921ca6bb43ef9f5fafdcfb36a400");
		assertEquals(beso.getPassword(), "p04325c580f21442da2517e241ef89c2e");
		beso = backEndServices.getBackEndService("redis1");
		assertEquals(beso.getInfo("name"),
				"e801ed2f-2515-4416-854b-463e9596c591");
		assertEquals(beso.getInfo("label"), "redis-2.6");
		assertEquals(beso.getHost(), "192.168.9.246");
		assertEquals(beso.getPort(), "5004");
		assertNull(beso.getUsername());
		assertEquals(beso.getPassword(), "15a7771e-da18-4ec2-9836-2551c581e38b");
		beso = backEndServices.getBackEndService("mongodb1");
		assertEquals(beso.getInfo("name"),
				"57b52c75-5815-42b2-80ed-af850ce1a1d3");
		assertEquals(beso.getInfo("label"), "mongodb-1.8");
		assertEquals(beso.getInfo("db"), "db");
		assertEquals(beso.getHost(), "192.168.9.244");
		assertEquals(beso.getPort(), "25006");
		assertEquals(beso.getUsername(), "ef262a3a-f442-4fad-940a-29696f77276c");
		assertEquals(beso.getPassword(), "133a143f-8dfa-44a3-8c60-46bc37c50fe9");
	}

	@Test
	public final void getServiceNamesList() {
		List<String> serviceNamesList = backEndServices.getServiceNamesList();
		for (String serviceName : serviceNamesList) {
			System.out.println("Service Name : " + serviceName);
		}
		assertTrue(serviceNamesList.contains("mypostgresqldb"));
		assertTrue(serviceNamesList.contains("mysql2"));
	}

	@Test
	public final void testCustom() {
		String servicesInfo = "{\"mysql-5.1\":[{\"name\":\"mysql2\",\"label\":\"mysql-5.1\",\"plan\":\"free\",\"tags\":[\"relational\",\"mysql-5.1\",\"mysql\"],\"credentials\":{\"name\":\"dd2dcd5b60e2c4fd1a1561598ea1f5da4\",\"hostname\":\"192.168.9.242\",\"host\":\"192.168.9.242\",\"port\":3306,\"user\":\"uQrvbcSR47s0H\",\"username\":\"uQrvbcSR47s0H\",\"password\":\"p6SANL6OzzQoi\"}}]}";
		backEndServices = new ClouluBackendServices(servicesInfo);
		List<String> serviceNamesList = backEndServices.getServiceNamesList();
		for (String serviceName : serviceNamesList) {
			System.out.println("Service Name : " + serviceName);
			BackendServiceObject backEndServiceObject = backEndServices
					.getBackEndService(serviceName);
			for (String key : backEndServiceObject.getKeys()) {
				System.out.println("System Property Key : " + key + " = "
						+ backEndServiceObject.getInfo(key));

			}
		}
	}
	
	@Test
	public final void testGetProperties() {
		Properties clProperties = backEndServices.getBackendServicesProperties();
		assertEquals(clProperties.getProperty("mypostgresqldb.name"),
				"dfc05d87f53d0402d918e55d61c1b0746");
		assertEquals(clProperties.getProperty("mypostgresqldb.label"),
				"postgresql-9.0");
		assertEquals(clProperties.getProperty("mypostgresqldb.host"),
				"192.168.9.243");
		assertEquals(clProperties.getProperty("mypostgresqldb.port"), "5433");
		assertEquals(clProperties.getProperty("mypostgresqldb.username"),
				"u5d16921ca6bb43ef9f5fafdcfb36a400");
		assertEquals(clProperties.getProperty("mypostgresqldb.password"),
				"p04325c580f21442da2517e241ef89c2e");
	}

}
