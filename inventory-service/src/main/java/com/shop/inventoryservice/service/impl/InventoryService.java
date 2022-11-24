package com.shop.inventoryservice.service.impl;

import com.shop.inventoryservice.repository.InventoryRepository;
import com.shop.inventoryservice.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InventoryService implements IInventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Boolean isStock(String sku) {

        return inventoryRepository.findBySku(sku).isPresent();
    }
}
