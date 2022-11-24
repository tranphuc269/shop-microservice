package com.shop.orderservice.service;

import com.shop.orderservice.dto.OrderRequest;

public interface IOrderService {

    public void placeOrder(OrderRequest orderRequest);

}
