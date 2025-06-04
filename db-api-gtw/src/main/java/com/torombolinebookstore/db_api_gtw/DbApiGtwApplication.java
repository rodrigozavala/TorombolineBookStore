package com.torombolinebookstore.db_api_gtw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableConfigurationProperties
@SpringBootApplication
public class DbApiGtwApplication {

	public static void main(String[] args) {

		SpringApplication.run(DbApiGtwApplication.class, args);
		/*
		Add a spring.config.import=configserver: property to your configuration.
	If configuration is not required add spring.config.import=optional:configserver: instead.
	To disable this check, set spring.cloud.config.enabled=false or
	spring.cloud.config.import-check.enabled=false.
		* */
	}

}
