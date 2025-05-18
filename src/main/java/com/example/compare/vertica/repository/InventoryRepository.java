package com.example.compare.vertica.repository;

import com.example.compare.vertica.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Integer> {

    List<Inventory> findByQtyInStockGreaterThan(Integer qtyInStock);

}

