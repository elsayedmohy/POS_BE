package com.sayed.pos.modules.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Data
public class AuthUserInfo {
    private Long id;
    private String name;
    private String email;
    private String storeName;
    private String role;
    private Instant createdAt;
}
