package com.rabbitshop.springproperties;

/**
 * We might also have a requirement to use different property values when our application is under test.
 *
 * Spring Boot handles this for us by looking in our “src/test/resources” directory during a test run. Again, default properties will still be
 * injectable as normal, but will be overridden these ones if there is a collision.
 */
// @Slf4j
// @ContextConfiguration
// @TestPropertySource("/application-configs/test-application.properties")
/**
 * If we don’t want to use a file, we can specify names and values directly:
 * @TestPropertySource("foo=bar", "bar=foo")
 */

// @RunWith(SpringRunner.class)
// @SpringBootTest
/**
 * We can also achieve a similar effect using the properties argument of the @SpringBootTest annotation:
 * 
 * @SpringBootTest(properties = {"foo=bar", "bar=foo"})
 */
public class SpringPropertiesApplicationTests {
	
	//	@Test
	public void contextLoads() {
		
		//		log.warn("Tests not yet implemented");
	}

}
