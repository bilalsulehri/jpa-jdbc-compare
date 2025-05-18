package com.example.compare.controllers;

import com.example.compare.services.InventoryService;
import com.example.compare.vertica.model.InventoryDao;
import com.example.compare.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VendorController {

    @Autowired
    VendorService vendorService;

    @Autowired
    InventoryService inventoryService;

    @PostMapping("/create-vendor")
    public ResponseEntity<String> createVendor(){
        vendorService.createVendor();
        return ResponseEntity.ok("vendor created");
    }

    @GetMapping("/get-inventory-entity")
    public ResponseEntity<List<InventoryDao>> getVendorsEntity(){
        long time =System.currentTimeMillis();
        List<InventoryDao> inventories = vendorService.getInventory();
        System.out.println("get inventories entity: "+(System.currentTimeMillis() - time ));
        System.out.println(inventories.size());
        return ResponseEntity.ok(inventories.stream().limit(100).collect(Collectors.toList()));
    }

    @GetMapping("/get-inventory-jdbc")
    public ResponseEntity<List<InventoryDao>> getVendorsJdbc(){
        long time =System.currentTimeMillis();
        List<InventoryDao> inventories = vendorService.getInventoryJdbc();
        System.out.println("get inventories jdbc: "+(System.currentTimeMillis() - time ));
        System.out.println(inventories.size());
        return ResponseEntity.ok(inventories.stream().limit(100).collect(Collectors.toList()));
    }

    @GetMapping("/get-inventory-jpa")
    public ResponseEntity<List<InventoryDao>> getVendorsJpa(@RequestParam("qty") int qty){
        long time =System.currentTimeMillis();
        List<InventoryDao> inventories = inventoryService.getInventoriesJpa(qty);
        System.out.println("get inventories jpa: "+(System.currentTimeMillis() - time ));
        System.out.println(inventories.size());
        return ResponseEntity.ok(inventories.stream().limit(100).collect(Collectors.toList()));
    }

}
