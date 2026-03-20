package com.sayed.pos.modules.store.service;


import com.sayed.pos.modules.store.dto.StoreResponse;
import com.sayed.pos.modules.store.entity.Store;
import org.springframework.stereotype.Service;

@Service
public interface StoreService {
    Store create(String name);
}
