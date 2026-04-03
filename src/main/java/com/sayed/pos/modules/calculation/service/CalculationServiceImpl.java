package com.sayed.pos.modules.calculation.service;

import com.sayed.pos.common.exception.BadRequestException;
import com.sayed.pos.modules.calculation.dto.CalculationItem;
import com.sayed.pos.modules.calculation.dto.CalculationResult;
import com.sayed.pos.modules.calculation.dto.SaleContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@AllArgsConstructor
@Service
public class CalculationServiceImpl implements CalculationService {

    private static final int SCALE = 2;

    @Override
    public CalculationResult calculate(SaleContext context) {

        validate(context);

        BigDecimal subtotal = calculateSubtotal(context.getItems());
        BigDecimal discount = calculateDiscount(
                subtotal,
                context.getDiscountType(),
                context.getDiscountValue()
        );

        BigDecimal taxableAmount = subtotal.subtract(discount);

        BigDecimal tax = calculateTax(
                taxableAmount,
                context.getTaxRate(),
                context.isEnableTaxes()
        );

        BigDecimal total = taxableAmount.add(tax);

        return new CalculationResult(
                scale(subtotal),
                scale(discount),
                scale(tax),
                scale(total)
        );
    }


    private BigDecimal calculateSubtotal(List<CalculationItem> items) {
        return items.stream()
                .map(i -> i.getPrice()
                        .multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateDiscount(BigDecimal subtotal,
                                         String type,
                                         BigDecimal value) {

        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal discount;

        if ("PERCENT".equalsIgnoreCase(type)) {
            discount = subtotal.multiply(value)
                    .divide(BigDecimal.valueOf(100));
        } else if ("FIXED".equalsIgnoreCase(type)) {
            discount = value;
        } else {
            return BigDecimal.ZERO;
        }

        if (discount.compareTo(subtotal) > 0) {
            throw new BadRequestException("Discount exceeds subtotal");
        }

        return discount;
    }

    private BigDecimal calculateTax(BigDecimal amount,
                                    BigDecimal taxRate,
                                    boolean enableTaxes) {

        if (!enableTaxes || taxRate == null || taxRate.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        return amount.multiply(taxRate)
                .divide(BigDecimal.valueOf(100));
    }


    private void validate(SaleContext context) {
        if (context.getItems() == null || context.getItems().isEmpty()) {
            throw new BadRequestException("Items cannot be empty");
        }

        for (var item : context.getItems()) {
            if (item.getQuantity() <= 0) {
                throw new BadRequestException("Invalid quantity");
            }
            if (item.getPrice() == null || item.getPrice().compareTo(BigDecimal.ZERO) < 0) {
                throw new BadRequestException("Invalid price");
            }
        }
    }

    private BigDecimal scale(BigDecimal value) {
        return value.setScale(SCALE, RoundingMode.HALF_UP);
    }
}
