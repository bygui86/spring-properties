package com.rabbitshop.springproperties.services.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.rabbitshop.springproperties.configs.pojos.DatabaseConfigs;
import com.rabbitshop.springproperties.configs.pojos.EmailConfigs;
import com.rabbitshop.springproperties.configs.pojos.OsEnvVarConfigs;
import com.rabbitshop.springproperties.configs.pojos.ValidationConfigs;
import com.rabbitshop.springproperties.services.PropertyTestService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PropertyTestServiceImpl implements PropertyTestService {
	
	private static final String DB_PASSWORD_PROPERTY = "database.password";

	private static final String RANDOM_INT_PROPERTY = "random.int";
	private static final String RANDOM_LONG_PROPERTY = "random.long";
	private static final String RANDOM_UUID_PROPERTY = "random.uuid";

	/**
	 * To obtain the value of a property with the new Environment API (part 1)
	 */
	@Resource(type = Environment.class)
	@Getter
	private Environment springEnvironment;
	
	@Resource(type = DatabaseConfigs.class)
	@Getter
	private DatabaseConfigs dbConfigs;
	
	@Resource(type = EmailConfigs.class)
	@Getter
	private EmailConfigs emailConfigs;

	@Resource(type = ValidationConfigs.class)
	@Getter
	private ValidationConfigs validConfigs;

	@Resource(type = OsEnvVarConfigs.class)
	@Getter
	private OsEnvVarConfigs osEnvVarConfigs;
	
	/**
	 * A default value of the property can also be specified
	 */
	@Value("${database.url:http://defaulthost:8888/database}")
	@Getter
	private String dbUrl;

	/**
	 * Injecting a property with the @Value annotation is straightforward
	 */
	@Value("${database.username}")
	@Getter
	private String dbUsername;
	
	@Getter
	@Setter
	private String dbPassword;
	
	@Override
	public void testPropertiesInjection() {

		log.info("*** Injected values ***");
		log.info("\tDatabase URL: " + getDbUrl());
		log.info("\tDatabase Username: " + getDbUsername());

		log.info("*** Retrieve values ***");
		log.info("\tRetrieving Database Password from property " + DB_PASSWORD_PROPERTY + " ...");
		setDbPassword();
		log.info("\tDatabase Password: " + getDbPassword());
	}

	protected void setDbPassword() {
		
		// To obtain the value of a property with the new Environment API (part 2)
		setDbPassword(getSpringEnvironment().getProperty(DB_PASSWORD_PROPERTY, String.class));
	}
	
	@Override
	public void testHierarchicalProperties() {

		log.info("Database hierarchical configs: " + getDbConfigs().toString());
	}
	
	@Override
	public void testHierarchicalPropertiesFromOtherFile() {
		
		log.info("Email hierarchical configs: " + getEmailConfigs().toString());
	}

	@Override
	public void testValidationProperties() {
		
		log.info("Validation configs: " + getValidConfigs().toString());
	}
	
	@Override
	public void testRandomProperties() {

		log.info("Random int: " + getSpringEnvironment().getProperty(RANDOM_INT_PROPERTY, int.class));
		log.info("Random long: " + getSpringEnvironment().getProperty(RANDOM_LONG_PROPERTY, long.class));
		log.info("Random uuid: " + getSpringEnvironment().getProperty(RANDOM_UUID_PROPERTY, String.class));
	}
	
	@Override
	public void testOsEnvVarProperties() {

		log.info("OS env-var home: " + getOsEnvVarConfigs().getHome());
		log.info("OS env-var projects: " + getOsEnvVarConfigs().getProjects());
	}
	
}
