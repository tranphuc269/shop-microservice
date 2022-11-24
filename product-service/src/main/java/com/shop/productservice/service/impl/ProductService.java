package com.shop.productservice.service.impl;

import com.shop.productservice.dto.ProductRequest;
import com.shop.productservice.dto.ProductResponse;
import com.shop.productservice.model.Product;
import com.shop.productservice.repository.ProductRepository;
import com.shop.productservice.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product
                .builder()
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .name(productRequest.getName())
                .build();
        Product productCreated = repository.save(product);
        return ProductResponse
                .builder()
                .id(productCreated.getId())
                .name(productCreated.getName())
                .description(productCreated.getDescription())
                .price(productCreated.getPrice())
                .build();
    }

    @Override
    public List<ProductResponse> getAllProduct() {

        List<Product> products = repository.findAll();
        List<ProductResponse> productResponseList = new ArrayList<>();
        products.forEach(v -> productResponseList.add(ProductResponse
                .builder()
                .id(v.getId())
                .name(v.getName())
                .description(v.getDescription())
                .price(v.getPrice())
                .build()));
        return productResponseList;
    }
}
