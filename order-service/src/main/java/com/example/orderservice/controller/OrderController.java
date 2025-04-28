package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping({"/api/order"})

public class OrderController {
    private final OrderService orderService;

    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackmethod")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @TimeLimiter(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {

        return CompletableFuture.supplyAsync(()->this.orderService.placeOrder(orderRequest));
    }

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    public CompletableFuture<String> fallbackmethod(OrderRequest orderRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(()->"Opps inventorty service is down here");

    }
}

