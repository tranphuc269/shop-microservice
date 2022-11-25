package com.shop.orderservice.service.impl;

import com.shop.orderservice.dto.OrderItemDTO;
import com.shop.orderservice.dto.OrderRequest;
import com.shop.orderservice.model.Order;
import com.shop.orderservice.model.OrderItem;
import com.shop.orderservice.repository.OrderItemRepository;
import com.shop.orderservice.repository.OrderRepository;
import com.shop.orderservice.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderItem> orderItemList = orderRequest
                .getOrderItemDTOList()
                .stream()
                .map(this::mapToDTO)
                .toList();
        order.setOrderItemList(orderItemList);
        order.setOrderId(orderRepository.save(order).getOrderId());
        orderItemList.forEach(v -> {
            v.setOrder(order);
        });
        // call inventory service, check product in stock
        Boolean result = webClientBuilder
                .build()
                .get()
                .uri("http://inventory-service/api/inventory/" + orderRequest.getOrderItemDTOList().get(0).getSku())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if (Boolean.TRUE.equals(result)) {
            orderItemRepository.saveAll(orderItemList);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again");
        }
    }


    private OrderItem mapToDTO(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(orderItemDTO.getPrice());
        orderItem.setSku(orderItemDTO.getSku());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        return orderItem;
    }
}
