package com.sayed.pos.modules.auth.service;


import com.sayed.pos.common.dto.Role;
import com.sayed.pos.common.exception.ConflictException;
import com.sayed.pos.common.exception.ResourceNotFoundException;
import com.sayed.pos.config.JwtConfig;
import com.sayed.pos.modules.auth.dto.*;
import com.sayed.pos.modules.auth.mapper.UserMapper;
import com.sayed.pos.modules.store.repository.StoreRepository;
import com.sayed.pos.modules.store.service.StoreService;
import com.sayed.pos.modules.user.dto.UserResponse;
import com.sayed.pos.modules.user.repository.UserRepository;
import com.sayed.pos.security.Jwt;
import com.sayed.pos.security.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final StoreRepository storeRepository;
    private final StoreService storeService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JwtConfig jwtConfig;
    private final Jwt jwt;


    @Override
    public AuthResponse login(LoginRequest request ,
                                                 HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        var cookie = new Cookie("refresh_token", refreshToken);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
        response.addCookie(cookie);
        return userMapper.toAuthResponse(user , accessToken);
    }

    @Override
    public UserResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ConflictException("Email already exists");
        }
        var store = storeService.create(registerRequest.getStoreName());
        var user = userMapper.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.ADMIN);
        user.setStore(store);
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public JwtResponse refresh(String token) {
        if (!jwt.validateToken(token)) {
            throw new BadCredentialsException("Invalid refresh token");
        }
        var userId = jwt.getUserIdFromToken(token);
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return new JwtResponse(jwtService.generateAccessToken(user));
    }
}
