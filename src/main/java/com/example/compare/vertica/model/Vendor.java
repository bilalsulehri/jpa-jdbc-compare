package com.example.compare.vertica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name="vendor_dimension")
public class Vendor {
    @Id
    @Column(name="vendor_key")
    private Integer vendorKey;

    @Column(name="vendor_name")
    private String vendorName;

    @Column(name="vendor_address")
    private String vendorAddress;

    @Column(name="vendor_city")
    private String vendorCity;

    @Column(name="vendor_state")
    private String vendorState;

    @Column(name="vendor_region")
    private String vendorRegion;

    @Column(name="deal_size")
    private Integer dealSize;

    @Column(name="last_deal_update")
    private Instant lastDealUpdate;

    public Integer getVendorKey() {
        return vendorKey;
    }

    public void setVendorKey(Integer vendorKey) {
        this.vendorKey = vendorKey;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorCity() {
        return vendorCity;
    }

    public void setVendorCity(String vendorCity) {
        this.vendorCity = vendorCity;
    }

    public String getVendorState() {
        return vendorState;
    }

    public void setVendorState(String vendorState) {
        this.vendorState = vendorState;
    }

    public String getVendorRegion() {
        return vendorRegion;
    }

    public void setVendorRegion(String vendorRegion) {
        this.vendorRegion = vendorRegion;
    }

    public Integer getDealSize() {
        return dealSize;
    }

    public void setDealSize(Integer dealSize) {
        this.dealSize = dealSize;
    }

    public Instant getLastDealUpdate() {
        return lastDealUpdate;
    }

    public void setLastDealUpdate(Instant lastDealUpdate) {
        this.lastDealUpdate = lastDealUpdate;
    }


    public Vendor(Integer vendorKey, String vendorName, String vendorAddress, String vendorCity, String vendorState, String vendorRegion, Integer dealSize, Instant lastDealUpdate) {
        this.vendorKey = vendorKey;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorCity = vendorCity;
        this.vendorState = vendorState;
        this.vendorRegion = vendorRegion;
        this.dealSize = dealSize;
        this.lastDealUpdate = lastDealUpdate;
    }

    public Vendor() {
    }


}
