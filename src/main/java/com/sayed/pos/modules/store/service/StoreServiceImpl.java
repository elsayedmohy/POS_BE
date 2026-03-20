package com.sayed.pos.modules.store.service;


import com.sayed.pos.modules.store.dto.StoreResponse;
import com.sayed.pos.modules.store.entity.Store;
import com.sayed.pos.modules.store.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store create(String name) {
        var  store = new Store();
        store.setName(name);
        storeRepository.save(store);
        return store;
    }
}
