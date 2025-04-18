package com.example.productservice.controller;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.productservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        this.productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return this.productService.getAllProducts();
    }
}
