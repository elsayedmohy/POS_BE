package com.sayed.pos.modules.auth.controller;

import com.sayed.pos.common.response.ApiResponse;
import com.sayed.pos.modules.auth.dto.*;
import com.sayed.pos.modules.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthUserInfo>> register(@Valid @RequestBody RegisterRequest request) {
        try {
            AuthUserInfo response = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success(response, "تم إنشاء الحساب بنجاح"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request , HttpServletResponse response) {
        try {
            AuthResponse loginResponse = authService.login(request ,response );
            return ResponseEntity
                    .ok(ApiResponse.success(loginResponse, "تم تسجيل الدخول بنجاح"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<JwtResponse>> refresh(@CookieValue("refresh_token") String token) {
        try {
            JwtResponse response = authService.refresh(token);
            return ResponseEntity
                    .ok(ApiResponse.success(response, "تم التجديد بنجاح"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }


}
