package com.torombolinebookstore.apigw.t_book_gw.service;


import com.torombolinebookstore.apigw.t_book_gw.client.AuthenticationClient;
import com.torombolinebookstore.common_models.auth_api.request.AuthenticationRequest;
import com.torombolinebookstore.common_models.auth_api.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    AuthenticationClient authenticationClient;


    public String get_token(AuthenticationRequest authenticationRequest){
        try{
            ResponseEntity<AuthenticationResponse> response = authenticationClient.authenticate(authenticationRequest);
            return response.getBody().getJwtToken();
        }catch (Exception e) {
            System.out.println("there is an issue");
        }
        return null;
    }
}
