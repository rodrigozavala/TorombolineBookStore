package com.torombolinebookstore.apigateway.t_book_gateway.service;

import com.torombolinebookstore.apigateway.t_book_gateway.client.AuthenticationClient;
import com.torombolinebookstore.common_models.model.CustomUserDetails;
import com.torombolinebookstore.common_models.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
        Optional<User> user = authenticationClient.findByEmail(username);// Assuming 'email' is used as username

        // Converting UserInfo to UserDetails
        return user.map(t -> new CustomUserDetails(t))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public String addUser(User userInfo) {
        // Encode password before saving the user
        userInfo.setPasswordHash(encoder.encode(userInfo.getPasswordHash()));
        authenticationClient.save(userInfo);
        return "User Added Successfully";
    }
}
