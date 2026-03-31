package com.sayed.pos.modules.auth.dto;


import com.sayed.pos.modules.user.dto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthResponse {
    private String token;
    private UserResponse user;
}
