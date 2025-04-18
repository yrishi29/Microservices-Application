package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Product;
import org.springframework.stereotype.Service;
import com.example.productservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription()).price(productRequest.getPrice()).build();
        this.productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getName()).description(product.getDescription()).price(product.getPrice()).build();
    }
}
