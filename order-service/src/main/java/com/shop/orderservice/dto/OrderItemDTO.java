package com.shop.orderservice.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDTO {
    private String sku;
    private BigDecimal price;
    private Integer quantity;
}
