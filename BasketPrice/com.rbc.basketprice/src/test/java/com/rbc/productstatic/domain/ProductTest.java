package com.rbc.productstatic.domain;

import com.rbc.productstatic.enums.UnitType;
import org.junit.Test;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class ProductTest {

    @Test
    public void checkProduct() {

        final int id = 1;
        final String productName = "TEST";
        final BigDecimal costPerUnit = BigDecimal.ONE;
        final BigDecimal unitQuantity = BigDecimal.TEN;
        final UnitType unitType = UnitType.BY_UNIT;

        Product product = new Product(id, productName, costPerUnit, unitQuantity, unitType);

        assertEquals(id, product.getId());
        assertEquals(productName, product.getProductName());
        assertEquals(costPerUnit, product.getCostPerUnit());
        assertEquals(unitQuantity, product.getUnitQuantity());
        assertEquals(unitType, product.getUnitType());

    }

}
