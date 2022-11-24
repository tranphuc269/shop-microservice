package com.shop.productservice.service;

import com.shop.productservice.dto.ProductRequest;
import com.shop.productservice.dto.ProductResponse;
import com.shop.productservice.model.Product;

import java.util.List;

public interface IProductService {

    ProductResponse createProduct(ProductRequest productRequest);


    List<ProductResponse> getAllProduct();

}
