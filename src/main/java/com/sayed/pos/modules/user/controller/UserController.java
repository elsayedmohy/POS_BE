package com.sayed.pos.modules.user.controller;


import com.sayed.pos.common.dto.Role;
import com.sayed.pos.common.response.ApiResponse;
import com.sayed.pos.modules.user.dto.UserRequest;
import com.sayed.pos.modules.user.dto.UserResponse;
import com.sayed.pos.modules.user.service.UsersService;
import com.sayed.pos.security.AuthUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersService usersService;

    @GetMapping("")
    ResponseEntity<ApiResponse<List<UserResponse>>> findAll(@AuthenticationPrincipal AuthUser user) {
        try {
            return ResponseEntity.ok(ApiResponse.success(
                    usersService.findAll(user.getUserId()), "success")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }

    }

    @PostMapping("")
    ResponseEntity<ApiResponse<UserResponse>> create(@AuthenticationPrincipal AuthUser user, @RequestBody UserRequest request) {
        Long storeId = user.getStoreId();
        if (!user.getRole().equals(Role.ADMIN)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("USER MUST BE OWNER"));
        }
        try {
            return ResponseEntity.ok(ApiResponse.success(
                    usersService.create(storeId, request), "success")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }

    }


    @PutMapping("/{userId}")
    ResponseEntity<ApiResponse<UserResponse>> update(@AuthenticationPrincipal AuthUser user, @PathVariable Long userId, @RequestBody UserRequest request) {
        try {
            return ResponseEntity.ok(ApiResponse.success(
                    usersService.update(user.getStoreId(), userId, request), "success")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long userId, @AuthenticationPrincipal AuthUser user) {
        try {
            usersService.delete(userId, user.getUserId());

            return ResponseEntity.ok(ApiResponse.success(null, String.valueOf(HttpStatus.NO_CONTENT)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }

    }

}
