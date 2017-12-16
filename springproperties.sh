#!bin/sh

# Prerequisites:
#	. Java
#	. Gradle
#	. Spring

# Compile with Gradle
./gradlew build

# Run the application with specific config-set:
	# [default] local
	java -jar build/libs/rs-springproperties-0.1.jar
	# test
	java -jar -DenvTarget=test build/libs/rs-springproperties-0.1.jar
	# prod
	java -jar -DenvTarget=prod build/libs/rs-springproperties-0.1.jar

# Run the application with additional properties passed via CLI ...
	# ... directly to the jar
	java -jar build/libs/rs-springproperties-0.1.jar --property.name="value"
	# ... via system properties, which are provided before the -jar command rather than after it
	java -Dproperty.name="value" -jar build/libs/rs-springproperties-0.1.jar

	# Spring Boot will also detect environment variables, treating them as properties:
	export property.name=value
	java -jar build/libs/rs-springproperties-0.1.jar
