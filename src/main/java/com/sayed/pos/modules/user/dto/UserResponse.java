package com.sayed.pos.modules.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String storeName;
    private String role;
    private Instant createdAt;
}
