package com.sayed.pos.modules.sale.service;

import com.sayed.pos.common.exception.BadRequestException;
import com.sayed.pos.common.exception.ResourceNotFoundException;
import com.sayed.pos.modules.product.entity.Product;
import com.sayed.pos.modules.product.repository.ProductRepository;
import com.sayed.pos.modules.sale.dto.SaleItemRequest;
import com.sayed.pos.modules.sale.dto.SaleRequest;
import com.sayed.pos.modules.sale.dto.SaleResponse;
import com.sayed.pos.modules.sale.entity.Sale;
import com.sayed.pos.modules.sale.entity.SaleItem;
import com.sayed.pos.modules.sale.exception.InsufficientStockException;
import com.sayed.pos.modules.sale.mapper.SaleMapper;
import com.sayed.pos.modules.sale.repository.SaleItemRepository;
import com.sayed.pos.modules.sale.repository.SaleRepository;
import com.sayed.pos.modules.store.entity.Store;
import com.sayed.pos.modules.store.repository.StoreRepository;
import com.sayed.pos.modules.user.entity.User;
import com.sayed.pos.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final SaleMapper saleMapper;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public SaleResponse createSale(Long userId , Long storeId, SaleRequest request) {

        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new BadRequestException("Sale items cannot be empty");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<Long> productIds = request.getItems().stream().map(SaleItemRequest::getProductId).toList();
        Map<Long, Product> productsMap = productRepository
                .findAllById(productIds)
                .stream()
                .collect(Collectors.toMap(Product::getId, p -> p));
        List<SaleItem> saleItems = new ArrayList<>();

        for (var item : request.getItems()) {
            var product =  productsMap.get(item.getProductId());
            if (product == null) {
                throw new ResourceNotFoundException("Product not found: " + item.getProductId());
            }
            if (product.getStock() >= item.getQuantity()) {
                totalAmount = totalAmount.add(
                        product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
                );
            } else {
                throw new InsufficientStockException(
                        "Not enough stock for product: " + product.getName()
                );
            }
        }

        var sale = new Sale();
        sale.setUser(user);
        sale.setStore(store);
        sale.setPaymentMethod(request.getPaymentMethod());
        sale.setTotalAmount(totalAmount);
        for (var item : request.getItems()) {
            var prod = productsMap.get(item.getProductId());
            var saleItem = new SaleItem();
            saleItem.setSale(sale);
            saleItem.setProduct(prod);
            saleItem.setPrice(prod.getPrice());
            saleItem.setQuantity(item.getQuantity());
            saleItems.add(saleItem);
            prod.setStock(prod.getStock() - item.getQuantity());
        }
        sale.getItems().addAll(saleItems);
        saleRepository.save(sale);
        return saleMapper.toResponse(sale);
    }
}
