package com.torombolinebookstore.apigw.t_book_gw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TBookGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TBookGatewayApplication.class, args);
	}

}
