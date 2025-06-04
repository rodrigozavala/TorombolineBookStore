package com.torombolinebookstore.authentication.controller;

import com.torombolinebookstore.authentication.service.CustomUserDetailsService;
import com.torombolinebookstore.common_models.model.User;
import com.torombolinebookstore.common_models.request.AuthenticationRequest;
import com.torombolinebookstore.common_models.request.RegistrationRequest;
import com.torombolinebookstore.common_models.response.AuthenticationResponse;
import com.torombolinebookstore.common_models.response.RegistrationResponse;
import com.torombolinebookstore.common_utils.utils.ByteOpsUtils;
import com.torombolinebookstore.common_utils.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private AuthenticationManager authenticationManager;

    private CustomUserDetailsService userService;

    @Autowired

    public void setAuthenticationManager(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setUserService(@Lazy CustomUserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome :D";
    }

    // Only authenticated users can see this
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> testAuth() {
        return ResponseEntity.ok("Authenticated :D");
    }

    // Everyone can see this
    @RequestMapping(value = "/test_no_auth", method = RequestMethod.GET)
    public ResponseEntity<String> testNoAuth() {
        return ResponseEntity.ok("Not authenticated :D");
    }


    @RequestMapping(value = "/get_token", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws Exception{

        AuthenticationResponse response = new AuthenticationResponse();
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( request.getEmail(), request.getPassword())
        );
        if (auth.isAuthenticated()){
            System.out.println("This is authenticated :D");
            String token = JwtUtils.generateToken(request.getEmail());
            response.setJwtToken(token);
        }else{
            throw new UsernameNotFoundException("Password or User incorrect");
        }
        return  ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest request) throws Exception{

        try{
            User userEntity = new User();
            userEntity.setUser_id(new byte[16]);
            userEntity.setPasswordHash(request.getPassword());
            userEntity.setEmail(request.getEmail());
            userEntity.setSalt(null);

            RegistrationResponse response = new RegistrationResponse(userService.addUser(userEntity),"200");

            return  ResponseEntity.ok(response);
        } catch (Exception e){
            // TODO: add logs here
            RegistrationResponse response = new RegistrationResponse(e.getMessage(),"400");
            return ResponseEntity.ok(response);
        }

    }

}
