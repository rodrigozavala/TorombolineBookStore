package com.torombolinebookstore.common_models.user_api.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginRequest {
    private String userEmail;
    private String password;
}
