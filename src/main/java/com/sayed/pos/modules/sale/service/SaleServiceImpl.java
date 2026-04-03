package com.sayed.pos.modules.sale.service;

import com.sayed.pos.common.exception.BadRequestException;
import com.sayed.pos.common.exception.ResourceNotFoundException;
import com.sayed.pos.modules.calculation.dto.CalculationItem;
import com.sayed.pos.modules.calculation.dto.SaleContext;
import com.sayed.pos.modules.calculation.service.CalculationService;
import com.sayed.pos.modules.product.dto.ProductResponse;
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
import com.sayed.pos.modules.settings.storesettings.entity.Settings;
import com.sayed.pos.modules.settings.storesettings.repository.SettingsRepository;
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

    private final CalculationService calculationService;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final SaleMapper saleMapper;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final SettingsRepository settingsRepository;

    @Override
    public List<SaleResponse> findAllByStoreId(Long storeId) {
        return saleRepository.findAllByStoreId(storeId)
                .stream()
                .map(saleMapper::toResponse).toList();
    }

    @Override
    public SaleResponse findById(Long saleId,Long storeId)  {
        var  sale = saleRepository.findByIdAndStoreId(saleId,storeId).orElseThrow(() -> new ResourceNotFoundException("Sale not found"));
        return saleMapper.toResponse(sale);
    }



    @Override
    @Transactional
    public SaleResponse create(Long userId , Long storeId, SaleRequest request) {

        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new BadRequestException("Sale items cannot be empty");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        Settings settings = settingsRepository.findById(storeId).orElseThrow(() -> new ResourceNotFoundException("Store Settings not found"));
        List<Long> productIds = request.getItems().stream().map(SaleItemRequest::getProductId).toList();
        Map<Long, Product> productsMap = productRepository
                .findAllById(productIds)
                .stream()
                .collect(Collectors.toMap(Product::getId, p -> p));
        List<SaleItem> saleItems = new ArrayList<>();
        List<CalculationItem> items = request.getItems().stream()
                .map(item -> {
                    Product product = productsMap.get(item.getProductId());

                    if (product == null) {
                        throw new ResourceNotFoundException("Product not found: " + item.getProductId());
                    }

                    return new CalculationItem(
                            product.getPrice(),
                            item.getQuantity()
                    );
                })
                .toList();

        var sale = new Sale();
        sale.setUser(user);
        sale.setStore(store);
        sale.setPaymentMethod(request.getPaymentMethod());
        var context = new SaleContext(
                items,
                request.getDiscountType(),
                request.getDiscountValue(),
                settings.getTaxPercentage(),
                settings.getEnableTaxes()
        );

        var result = calculationService.calculate(context);
        sale.setSubtotal(result.getSubtotal());
        sale.setTaxAmount(result.getTaxAmount());
        sale.setDiscountAmount(result.getDiscountAmount());
        sale.setTotalAmount(result.getTotal());
        sale.setCustomerName(
                (request.getCustomerName() != null && !request.getCustomerName().isEmpty())
                        ? request.getCustomerName()
                        : "Walk-in"
    );
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

    @Override
    public SaleResponse update(Long storeId, Long saleId, SaleRequest request) {
        return null;
    }

    @Override
    public void deleteById(Long saleId ,Long storeId) {
        var  sale = saleRepository.findByIdAndStoreId(saleId,storeId).orElse(null);
        if (sale == null) {
            throw new ResourceNotFoundException("Sale not found");
        }
        saleRepository.deleteById(saleId);
    }
}
