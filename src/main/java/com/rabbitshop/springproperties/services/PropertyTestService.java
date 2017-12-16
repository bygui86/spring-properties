package com.rabbitshop.springproperties.services;

public interface PropertyTestService {

	void testPropertiesInjection();
	
	void testHierarchicalProperties();
	
	void testHierarchicalPropertiesFromOtherFile();

	void testValidationProperties();
	
	void testRandomProperties();

	void testOsEnvVarProperties();

}
