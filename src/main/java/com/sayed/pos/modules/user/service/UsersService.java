package com.sayed.pos.modules.user.service;

import com.sayed.pos.modules.user.dto.UserRequest;
import com.sayed.pos.modules.user.dto.UserResponse;
import com.sayed.pos.modules.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UsersService {
    List<UserResponse> findAll(Long storeId);
    UserResponse create(Long storeId, UserRequest request);
    UserResponse update(Long storeId,Long userId,UserRequest request);
    void delete(Long storeId,Long userId);

}
