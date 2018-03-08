package com.keblal.cxfdemo;

import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.keblal.cxfdemo.scanners.RestProviderBeanScanner;
import com.keblal.cxfdemo.scanners.RestServiceBeanScanner;

/*
 * reference : https://stackoverflow.com/questions/8075790/how-to-register-spring-configuration-annotated-class-instead-of-applicationcont
 */

/*
 *  this is the 'class' equivalent of Application context
 */

@Configuration
@ComponentScan("com.keblal.cxfdemo")
public class ApplnContext {

	@ApplicationPath("/")
	public class JaxRsApiApplication extends Application {
	}

	@Bean(destroyMethod = "shutdown")
	public SpringBus cxf() {
		return new SpringBus();
	}

	@Bean
	@DependsOn("cxf")
	public Server jaxRsServer(ApplicationContext appContext) {
		JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint(jaxRsApiApplication(),
				JAXRSServerFactoryBean.class);
		factory.setServiceBeans(restServiceList(appContext));
		factory.setAddress("/" + factory.getAddress());						// address from which the API begins.
		factory.setProviders(restProviderList(appContext, jsonProvider()));
		return factory.create();
	}

	@Bean
	public JacksonJsonProvider jsonProvider() {
		return new JacksonJsonProvider();
	}

	@Bean
	public JaxRsApiApplication jaxRsApiApplication() {
		return new JaxRsApiApplication();
	}

	private List<Object> restServiceList(ApplicationContext appContext) {						
		// services (AKA) endpoints need to be under com.keblal.cxfdemo.services
		return RestServiceBeanScanner.scan(appContext, new String[] {"com.keblal.cxfdemo.services", "com.keblal.cxfdemo.resources"});
	}

	private List<Object> restProviderList(ApplicationContext appContext, final JacksonJsonProvider jsonProvider) {  
		// when returning objects, the provider JacksonJsonProvider is used to convert response to JSON.
		final List<Object> providers = RestProviderBeanScanner.scan(appContext, "com.keblal.cxfdemo.provider");
		providers.add(jsonProvider);
		return providers;
	}

}
