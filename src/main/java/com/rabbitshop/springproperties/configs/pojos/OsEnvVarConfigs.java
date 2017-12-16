package com.rabbitshop.springproperties.configs.pojos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * If we have properties which are grouped together, we can make use of the @ConfigurationProperties annotation which will map these property
 * hierarchies into Java objects graphs.
 *
 * Spring Boot applies it’s convention over configuration approach again, automatically mapping between property names and their corresponding
 * fields. All that we need to supply is the property prefix.
 *
 * PLEASE NOTE
 *	. According to the official documentation, it is recommended to isolate the configuration properties into a separate POJO annotated with
 * 	  @ConfigurationProperties.
 *	. We also have to add the @Configuration annotation for Spring to be able to find this bean and make it a candidate for injection.
 *	. Also, by default, a relaxed binding scheme is adopted for the binding, so all of the following variations are bound to the property
 *    authMethod of the Credentials class:
 *    	. mail.credentials.auth_method
 *    	. mail.credentials.auth-method
 *    	. mail.credentials_AUTH_METHOD
 *    	. mail.CREDENTIALS_AUTH_METHOD
 *	. Similarly, List and Map properties can also be bound.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Configuration
@ConfigurationProperties(prefix = "os.envvar")
public class OsEnvVarConfigs {

	private String home;

	private String projects;

}
