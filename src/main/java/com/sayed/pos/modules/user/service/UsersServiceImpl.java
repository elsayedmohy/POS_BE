package com.sayed.pos.modules.user.service;

import com.sayed.pos.common.dto.Role;
import com.sayed.pos.common.exception.BadRequestException;
import com.sayed.pos.common.exception.ConflictException;
import com.sayed.pos.common.exception.ResourceNotFoundException;
import com.sayed.pos.modules.auth.mapper.UserMapper;
import com.sayed.pos.modules.store.repository.StoreRepository;
import com.sayed.pos.modules.store.service.StoreService;
import com.sayed.pos.modules.user.dto.UserRequest;
import com.sayed.pos.modules.user.dto.UserResponse;
import com.sayed.pos.modules.user.entity.User;
import com.sayed.pos.modules.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StoreService storeService;
    private final UserMapper userMapper;
    private final StoreRepository storeRepository;

    @Override
    public List<UserResponse> findAll(Long storeId) {
        List<User> users = userRepository.findAllByStoreId(storeId);
        if (users == null) {
            throw new BadRequestException("Users not found");
        }
        return users.stream().map(userMapper::toResponse).toList();
    }

    @Override
    public UserResponse create(Long storeId, UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email already exists");
        }
        var store = storeRepository.findById(storeId).orElseThrow(() -> new ResourceNotFoundException("store not sound"));
        var user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode("123456"));
        user.setRole(request.getRole());
        user.setStore(store);
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse update(Long storeId, Long userId, UserRequest request) {
        var user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setName(request.getName());
        var saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    public void delete(Long storeId, Long userId) {
        var user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        if (user.getRole().equals(Role.ADMIN)) {
            throw new BadRequestException("Store owners cannot be deleted");
        }
        userRepository.delete(user);
    }


}
