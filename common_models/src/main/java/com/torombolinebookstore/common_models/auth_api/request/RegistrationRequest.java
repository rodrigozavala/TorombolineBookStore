package com.torombolinebookstore.common_models.auth_api.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    private String email;
    private String password;

}
