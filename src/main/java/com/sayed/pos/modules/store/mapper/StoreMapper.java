package com.sayed.pos.modules.store.mapper;


import com.sayed.pos.modules.store.dto.StoreResponse;
import com.sayed.pos.modules.store.entity.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    StoreResponse toStoreResponse(Store store);
}
