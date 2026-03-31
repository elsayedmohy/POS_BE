package com.sayed.pos.security;

import com.sayed.pos.common.dto.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUser {
    private Long userId;
    private Long storeId;
    private Role role;
}
