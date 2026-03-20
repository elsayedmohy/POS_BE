package com.sayed.pos.modules.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthResponse {
    private String token;
    private AuthUserInfo user;
}
