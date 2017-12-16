package com.rabbitshop.springproperties.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import lombok.extern.slf4j.Slf4j;

/**
 * Configuration must be environment specific – that’s just a fact of life. If that weren’t the case, then it wouldn’t be configuration and we would
 * just hardcode values in code.
 *
 * For a Spring application there are several solutions you can use – from simple solutions all the way to uber-flexible, highly complex alternatives.
 * One of more common and straightforward solutions is a flexible use of properties files and the first class property support provided by Spring.
 *
 * Before Spring 3.1, adding new properties files into Spring and using property values wasn’t as flexible and as robust as it could be. Starting with
 * Spring 3.1, the new Environment and PropertySource abstractions have simplified then entire process.
 *
 * This approach allows for the flexibility of having multiple *.properties files for specific, focused purposes. For example the security config would
 * import security related properties and so on.
 *
 * The final, deployable war will contain all properties files. Since the files are actually named differently, there is no fear of accidentally including
 * the wrong one. We will set the envTarget variable and thus select the instance we want from the multiple existing variants.
 * The envTarget variable can be set in the OS/environment or as a parameter to the JVM command line:
 * 		-DenvTarget=dev
 *
 * There are several ways to build additional flexibility into this solution if needed. One such way is to use a more complex encoding for the names of
 * the properties files, specifying not just the environment in which they are to be used, but also more information (such as the persistence provider).
 *
 * By default, in Spring 4, local properties are search last, after all, environment property sources, including properties files. A quick sidenote here is that
 * local properties are properties that are configured manually/programmatically via the setter APIs in the base PropertiesLoaderSupport class (setProperties,
 * setLocation, etc).
 *
 * This behavior can be overridden via the localOverride property of the PropertySourcesPlaceholderConfigurer, which can be set to true to allow local properties
 * to override file properties.
 *
 * Spring Boot applies it’s typical convention over configuration approach to property files. This means that we can simply put an “application.properties”
 * file in our “src/main/resources” directory, and it will be auto-detected. We can then inject any loaded properties from it as normal.
 * So, by using this default file, we don’t have to explicitly register a PropertySource, or even provide a path to a property file.
 * We can also configure a different file at runtime if we need to, using an environment property:
 * 		java -jar app.jar --spring.config.location=classpath:/another-location.properties
 *
 * PLEASE NOTE
 *	. In a typical Maven application, these can reside in src/main/resources, but the wherever they are, they will need to be available on the classpath
 *	  when the application is deployed. We just need to specify the right path in the @PropertySource annotation.
 *	. *** IMPORTANT *** having all properties files under version control makes configuration much more transparent and reproducible. This is in
 *	  opposition to having the configs on disk somewhere and simply pointing Spring to them.
 *	. *** IMPORTANT *** Properties in Parent-Child Contexts
 *		.. If the properties file is defined in XML with <property-placeholder>, any other context (different than that is importing the properties) won't
 *		   see these properties
 *		.. If the properties file is defined in Java with @PropertySource
 *			... If the file is defined in the Parent context:
 *					. @Value works in Child context: YES
 *					. @Value works in Parent context: YES
 *					. environment.getProperty in Child context: YES
 *					. environment.getProperty in Parent context: YES
 *			... If the file is defined in the Child context:
 *					. @Value works in Child context: YES
 *					. @Value works in Parent context: NO
 *					. environment.getProperty in Child context: YES
 *					. environment.getProperty in Parent context: NO
 */
@Slf4j
@Configuration
/**
 * Spring 3.1 also introduces the new @PropertySource annotation, as a convenient mechanism for adding property sources to the environment.
 * This annotation is to be used in conjunction with Java based configuration and the @Configuration annotation.
 * This placeholder can be static (fixed file name) or dynamic (using a Java variable).
 *
 * PLEASE NOTE
 * In case a property is defined in two or more files defined via @PropertySource, the last definition will win and override the previous ones.
 * This makes the exact property value hard to predict, so if overriding is important, the PropertySource API can be used instead.
 */
@PropertySource({ "classpath:/application-configs/${envTarget:local}-application.properties" })
public class PropertyFilesConfig {

	/**
	 * As opposed to using XML namespace element, the Java @PropertySource annotation does not automatically register a
	 * PropertySourcesPlaceholderConfigurer with Spring.
	 *
	 * Instead, the bean must be explicitly defined in the configuration to get the property resolution mechanism working.
	 * The reasoning behind this unexpected behavior is by design and documented on {@link https://jira.springsource.org/browse/SPR-8539}.
	 *
	 * Both the older {@link org.springframework.beans.factory.config.PropertyPlaceholderConfigurer} and the new
	 * {@link org.springframework.context.support.PropertySourcesPlaceholderConfigurer.PropertySourcesPlaceholderConfigurer()}
	 * added in Spring 3.1 resolve ${...} placeholders within bean definition property values and @Value annotations.
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

		log.debug("Registering PropertySources placeholder");

		final PropertySourcesPlaceholderConfigurer propertyPlaceholder = new PropertySourcesPlaceholderConfigurer();
		// TODO Specifying here the localtion of the properties file, we are not able to use the Java variable "envTarget", how to do that?
		// propertyPlaceholder.setLocation(new ClassPathResource("/application-configs/local-application.properties"));
		propertyPlaceholder.setOrder(1);
		// propertyPlaceholder.setNullValue("");
		// propertyPlaceholder.setLocalOverride(true);
		return propertyPlaceholder;
	}

}
