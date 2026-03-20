package com.sayed.pos.modules.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthUserInfo {
    private String name;
    private String email;
    private String storeName;
}
