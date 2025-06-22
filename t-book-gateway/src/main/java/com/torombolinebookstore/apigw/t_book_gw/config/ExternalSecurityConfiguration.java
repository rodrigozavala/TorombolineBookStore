package com.torombolinebookstore.apigw.t_book_gw.config;

import com.torombolinebookstore.apigw.t_book_gw.entry_point.LoginRedirectAuthenticationEntryPoint;
import com.torombolinebookstore.common_utils.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class ExternalSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())// Disable CSRF (not needed for stateless JWT)
                // Configure endpoint authorization
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/login").permitAll()
                        .requestMatchers("/user/register").permitAll()
                        // Role-based endpoints
                        .requestMatchers("/user/test").authenticated()//.hasRole("USER")
                        // All other endpoints require authentication
                        .anyRequest().authenticated()
                )// Setting a stateless session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                /* Will use oauth2ResourceServer to take JWTs to decode them using my
                * jwtDecoder(jwtSecretKey()) (to get a HMAC512 decocer) and
                * jwtAuthenticationConverter() (to get an authentication converter to get Roles ingrained
                * into the JWT)
                * */
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt-> {
                             jwt.decoder(jwtDecoder(jwtSecretKey()))
                                .jwtAuthenticationConverter(jwtAuthenticationConverter());})
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new LoginRedirectAuthenticationEntryPoint("/login"))
                );
                // Set custom authentication provider
                //.authenticationProvider(authenticationProvider())
                // Add JWT filter before Spring Security's default filter TODO: must check what is this
                //UsernamePasswordAuthenticationFilter
                //.addFilter(jwtAuthFilter);

        return http.build();
    }


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        // Still not in use, I must find a way to add a ROLE in my JWTs to experiment with JWT authorization
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return converter;
    }


    @Bean
    public JwtDecoder jwtDecoder(SecretKey jwtSecretKey) {
        return NimbusJwtDecoder.withSecretKey(jwtSecretKey).macAlgorithm(MacAlgorithm.HS512).build();
    }

    @Bean
    public SecretKey jwtSecretKey() {
        // Replace with your actual fetching logic from Vault, AWS Secrets Manager, etc.
        String base64Secret = fetchSecretFromExternalSource();
        byte[] decodedKey = Base64.getDecoder().decode(base64Secret);
        return new SecretKeySpec(decodedKey, "HmacSHA512");
    }

    private String fetchSecretFromExternalSource() {
        // This could be a call to AWS Secrets Manager, Vault, etc.
        return JwtUtils.SECRET;//"REPLACE_WITH_BASE64_ENCODED_SECRET";
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
