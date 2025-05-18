package com.example.compare.vertica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="inventory_fact")
public class Inventory {
    @Id
    @Column(name = "date_key")
    private Integer dateKey;

    @Column(name = "product_key")
    private Integer productKey;

    @Column(name = "product_version")
    private Integer productVersion;

    @Column(name = "warehouse_key")
    private Integer warehouseKey;

    @Column(name = "qty_in_stock")
    private Integer qtyInStock;

    @Column(name = "inventory_date")
    private Date inventoryDate;

    public Inventory(Integer dateKey, Integer productKey, Integer productVersion, Integer warehouseKey, Integer qtyInStock, Date inventoryDate) {
        this.dateKey = dateKey;
        this.productKey = productKey;
        this.productVersion = productVersion;
        this.warehouseKey = warehouseKey;
        this.qtyInStock = qtyInStock;
        this.inventoryDate = inventoryDate;
    }

    public Inventory() {
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

