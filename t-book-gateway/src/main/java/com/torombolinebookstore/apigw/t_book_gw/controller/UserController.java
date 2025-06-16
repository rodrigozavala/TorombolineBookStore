package com.torombolinebookstore.apigw.t_book_gw.controller;


import com.torombolinebookstore.apigw.t_book_gw.service.LoginService;
import com.torombolinebookstore.common_models.auth_api.request.AuthenticationRequest;
import com.torombolinebookstore.common_models.auth_api.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    public LoginService loginService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {

        String result = loginService.get_token(request);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setJwtToken(result);
        System.out.println("The message is blabla: "+ String.valueOf(result));


        return  ResponseEntity.ok(response);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody AuthenticationRequest request) throws Exception{

        AuthenticationResponse response = new AuthenticationResponse();


        return  ResponseEntity.ok(response);
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> testUser(@RequestBody AuthenticationRequest request) throws Exception{

        AuthenticationResponse response = new AuthenticationResponse();


        return  ResponseEntity.ok(response);
    }


}
