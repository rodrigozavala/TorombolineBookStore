package com.torombolinebookstore.apigw.t_book_gw.config;


import com.torombolinebookstore.apigw.t_book_gw.client.AuthenticationClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FeignClientsConfiguration.class)
public class ClientProviderConfiguration {


    private AuthenticationClient authenticationClient;

    public ClientProviderConfiguration(){
        //Configure client here if needed
    }

    @Bean
    public AuthenticationClient getAuthenticationClient(){
        return authenticationClient;
    }
}
