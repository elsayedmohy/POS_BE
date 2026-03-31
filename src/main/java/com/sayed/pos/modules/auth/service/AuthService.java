package com.sayed.pos.modules.auth.service;


import com.sayed.pos.modules.auth.dto.*;
import com.sayed.pos.modules.user.dto.UserResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthResponse login(LoginRequest loginRequest , HttpServletResponse response);
    UserResponse register(RegisterRequest registerRequest);
    JwtResponse refresh(String token);

}
