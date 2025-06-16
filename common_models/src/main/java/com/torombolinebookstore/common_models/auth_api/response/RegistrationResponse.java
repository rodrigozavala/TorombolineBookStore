package com.torombolinebookstore.common_models.auth_api.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {
    private String responseMessage;
    private String responseStatus;
}
