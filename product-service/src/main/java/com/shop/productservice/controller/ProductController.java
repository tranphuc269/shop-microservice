package com.shop.productservice.controller;


import com.shop.productservice.dto.ProductRequest;
import com.shop.productservice.dto.ProductResponse;
import com.shop.productservice.model.Product;
import com.shop.productservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse product = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping
    public ResponseEntity<?> getProducts(){
        List<ProductResponse> products = productService.getAllProduct();
        return ResponseEntity.ok(products);
    }
}
