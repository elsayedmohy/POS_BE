package com.sayed.pos.modules.auth.service;


import com.sayed.pos.modules.auth.dto.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthResponse login(LoginRequest loginRequest , HttpServletResponse response);
    AuthUserInfo register(RegisterRequest registerRequest);
    JwtResponse refresh(String token);

}
