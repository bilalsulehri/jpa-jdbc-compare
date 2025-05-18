package com.example.compare.vertica.model;

import java.sql.Date;

public class InventoryDao {

    private Integer dateKey;
    private Integer productKey;
    private Integer productVersion;
    private Integer warehouseKey;
    private Integer qtyInStock;
    private Date inventoryDate;

    public InventoryDao(Integer dateKey, Integer productKey, Integer productVersion, Integer warehouseKey, Integer qtyInStock, Date inventoryDate) {
        this.dateKey = dateKey;
        this.productKey = productKey;
        this.productVersion = productVersion;
        this.warehouseKey = warehouseKey;
        this.qtyInStock = qtyInStock;
        this.inventoryDate = inventoryDate;
    }
    public InventoryDao(){}

    public InventoryDao(Inventory inventory) {
        this.dateKey = inventory.getDateKey();
        this.productKey = inventory.getProductKey();
        this.productVersion = inventory.getProductVersion();
        this.warehouseKey = inventory.getWarehouseKey();
        this.qtyInStock = inventory.getQtyInStock();
        this.inventoryDate = inventory.getInventoryDate();
    }
    public Integer getDateKey() {
        return dateKey;
    }

    public void setDateKey(Integer dateKey) {
        this.dateKey = dateKey;
    }

    public Integer getProductKey() {
        return productKey;
    }

    public void setProductKey(Integer productKey) {
        this.productKey = productKey;
    }

    public Integer getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(Integer productVersion) {
        this.productVersion = productVersion;
    }

    public Integer getWarehouseKey() {
        return warehouseKey;
    }

    public void setWarehouseKey(Integer warehouseKey) {
        this.warehouseKey = warehouseKey;
    }

    public Integer getQtyInStock() {
        return qtyInStock;
    }

    public void setQtyInStock(Integer qtyInStock) {
        this.qtyInStock = qtyInStock;
    }

    public Date getInventoryDate() {
        return inventoryDate;
    }

    public void setInventoryDate(Date inventoryDate) {
        this.inventoryDate = inventoryDate;
    }

}
