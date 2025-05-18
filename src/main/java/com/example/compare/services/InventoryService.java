package com.example.compare.services;

import com.example.compare.vertica.model.InventoryDao;
import com.example.compare.vertica.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public List<InventoryDao> getInventoriesJpa(int qty){
        return inventoryRepository.findByQtyInStockGreaterThan(qty).stream().map(InventoryDao::new).collect(Collectors.toList());
    }

}
