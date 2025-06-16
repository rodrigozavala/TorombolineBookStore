package com.torombolinebookstore.apigw.t_book_gw.service;

import com.torombolinebookstore.apigw.t_book_gw.client.AuthenticationClient;
import com.torombolinebookstore.common_models.auth_api.request.AuthenticationRequest;
import com.torombolinebookstore.common_models.auth_api.request.RegistrationRequest;
import com.torombolinebookstore.common_models.auth_api.response.AuthenticationResponse;
import com.torombolinebookstore.common_models.model.CustomUserDetails;
import com.torombolinebookstore.common_models.model.User;
import com.torombolinebookstore.common_utils.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ExternalUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthenticationClient authenticationClient;

    private PasswordEncoder encoder;

    @Autowired
    public void setEncoder(@Lazy PasswordEncoder encoder){
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        //authenticationRequest.;
        Optional<User> user = Optional.of(new User());
        try{
            ResponseEntity<AuthenticationResponse> response = authenticationClient.authenticate(new AuthenticationRequest());// Assuming 'email' is used as username
            String jwtToken = response.getBody().getJwtToken();
            String userName = JwtUtils.extractUsername(jwtToken);

            user.get().setEmail(userName);
            //return new CustomUserDetails(user);

        } catch (Exception e){
            // TODO: implement logs
            System.out.println("Something happened "+ e.getMessage());
            throw new UsernameNotFoundException(e.getMessage());
        }

        // Converting UserInfo to UserDetails
        return user.map(t -> new CustomUserDetails(t))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid token for " + username));
    }

    public String addUser(User userInfo) {
        // Encode password before saving the user
        userInfo.setPasswordHash(encoder.encode(userInfo.getPasswordHash()));
        try{
            authenticationClient.register(new RegistrationRequest());
        }catch ( Exception e){
            // TODO: implement logs
            System.out.println("Random logs here");
        }
        return "User Added Successfully";
    }
}
