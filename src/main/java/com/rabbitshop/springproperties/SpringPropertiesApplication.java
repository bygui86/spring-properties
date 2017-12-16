package com.rabbitshop.springproperties;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitshop.springproperties.services.PropertyTestService;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringPropertiesApplication implements CommandLineRunner {
	
	@Resource(type = PropertyTestService.class)
	@Getter
	private PropertyTestService propertyTestService;
	
	/**
	 * THE MAIN
	 */
	public static void main(final String[] args) {
		
		SpringApplication.run(SpringPropertiesApplication.class, args);
	}
	
	/**
	 * THE RUN
	 */
	@Override
	public void run(final String... arg0) throws Exception {
		
		log.info("");
		log.info("");
		getPropertyTestService().testPropertiesInjection();
		log.info("");
		log.info("");
		getPropertyTestService().testHierarchicalProperties();
		log.info("");
		log.info("");
		getPropertyTestService().testHierarchicalPropertiesFromOtherFile();
		log.info("");
		log.info("");
		getPropertyTestService().testValidationProperties();
		log.info("");
		log.info("");
		getPropertyTestService().testRandomProperties();
		log.info("");
		log.info("");
		getPropertyTestService().testOsEnvVarProperties();
		log.info("");
		log.info("");
	}
	
}
