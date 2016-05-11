package com.rbc.productstatic.domain;

import com.rbc.productstatic.enums.UnitType;

import java.math.BigDecimal;

public class Product {

    private final int id;
    private final String productName;
    private final BigDecimal costPerUnit;
    private final BigDecimal unitQuantity;
    private final UnitType unitType;

    public Product(int id, String productName, BigDecimal costPerUnit, BigDecimal unitQuantity, UnitType unitType) {
        this.id = id;
        this.productName = productName;
        this.costPerUnit = costPerUnit;
        this.unitQuantity = unitQuantity;
        this.unitType = unitType;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getCostPerUnit() {
        return costPerUnit;
    }

    public BigDecimal getUnitQuantity() {
        return unitQuantity;
    }

    public UnitType getUnitType() {
        return unitType;
    }

}