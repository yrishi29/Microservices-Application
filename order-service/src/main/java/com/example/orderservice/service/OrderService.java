package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private  OrderRepository orderRepository;
    private  WebClient.Builder webClient;

    @Autowired
    public OrderService(WebClient.Builder webClientBuilder, OrderRepository orderRepository) {
        this.webClient = webClientBuilder;
        this.orderRepository = orderRepository;
    }

    public String placeOrder(OrderRequest orderRequest) {
        Order order = mapToOrder(orderRequest);


        Boolean result = webClient.build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("inventory-service")  //
                        .path("/api/inventory")
                        .queryParam("skuCode", order.getSkuCode())
                        .queryParam("quantity", order.getQuantity())
                        .build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.FALSE.equals(result)) {
            throw new RuntimeException("Product is out of stock!");
        }


        orderRepository.save(order);
        return "Order Placed Successfully";
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
