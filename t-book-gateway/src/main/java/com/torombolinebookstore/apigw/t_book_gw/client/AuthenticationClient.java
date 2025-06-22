package com.torombolinebookstore.apigw.t_book_gw.client;


import com.torombolinebookstore.apigw.t_book_gw.config.FeignClientConfig;
import com.torombolinebookstore.common_models.auth_api.request.AuthenticationRequest;
import com.torombolinebookstore.common_models.auth_api.request.RegistrationRequest;
import com.torombolinebookstore.common_models.auth_api.response.AuthenticationResponse;
import com.torombolinebookstore.common_models.auth_api.response.RegistrationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "authentication", url = "localhost:8090/auth", configuration = FeignClientConfig.class)
public interface AuthenticationClient {

    @GetMapping("/welcome")
    public String welcome();

    // Only authenticated users can see this
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> testAuth();

    // Everyone can see this
    @RequestMapping(value = "/test_no_auth", method = RequestMethod.GET)
    public ResponseEntity<String> testNoAuth();

    @RequestMapping(value = "/get_token", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws Exception;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest request) throws Exception;

}
