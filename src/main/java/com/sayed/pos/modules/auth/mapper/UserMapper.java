package com.sayed.pos.modules.auth.mapper;

import com.sayed.pos.modules.auth.dto.AuthResponse;
import com.sayed.pos.modules.auth.dto.AuthUserInfo;
import com.sayed.pos.modules.auth.dto.RegisterRequest;
import com.sayed.pos.modules.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterRequest request);
    AuthResponse toAuthResponse(User user,String token);
    @Mapping(source = "store.name", target = "storeName")
    AuthUserInfo toRegisterResponse(User user);
}
