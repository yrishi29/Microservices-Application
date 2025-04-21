package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, WebClient.Builder webClient) {
        this.orderRepository = orderRepository;
        this.webClient = webClient;
    }

    public void placeOrder(OrderRequest orderRequest) {
        Order order = mapToOrder(orderRequest);
        Boolean result = (Boolean)this.webClient.build().get().uri("http://localhost:59063/api/inventory?skuCode=" + order.getSkuCode() + "&quantity=" + order.getQuantity(), new Object[0]).retrieve().bodyToMono(Boolean.class).block();
        if (Boolean.FALSE.equals(result)) {
            throw new RuntimeException("Product is out of stock!");
        }
    }

    private static Order mapToOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        order.setSkuCode(orderRequest.skuCode());
        return order;
    }
}

