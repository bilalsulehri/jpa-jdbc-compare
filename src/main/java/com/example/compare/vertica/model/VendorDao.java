package com.example.compare.vertica.model;


import java.time.Instant;

public class VendorDao {

    private Integer vendorKey;

    private String vendorName;

    private String vendorAddress;

    private String vendorCity;

    private String vendorState;


    private String vendorRegion;


    private Integer dealSize;
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


    public VendorDao(Integer vendorKey, String vendorName, String vendorAddress, String vendorCity, String vendorState, String vendorRegion, Integer dealSize, Instant lastDealUpdate) {
        this.vendorKey = vendorKey;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorCity = vendorCity;
        this.vendorState = vendorState;
        this.vendorRegion = vendorRegion;
        this.dealSize = dealSize;
        this.lastDealUpdate = lastDealUpdate;
    }

    public VendorDao() {
    }

    public VendorDao(Vendor vendor){
        this.vendorKey = vendor.getVendorKey();
        this.vendorName = vendor.getVendorName();
        this.vendorAddress = vendor.getVendorAddress();
        this.vendorCity = vendor.getVendorCity();
        this.vendorState = vendor.getVendorState();
        this.vendorRegion = vendor.getVendorRegion();
        this.dealSize = vendor.getDealSize();
        this.lastDealUpdate = vendor.getLastDealUpdate();
    }


}
