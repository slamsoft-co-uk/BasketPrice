package com.rbc.basketprice.services.impl;

import com.rbc.basketprice.domain.Basket;
import com.rbc.basketprice.domain.BasketItem;
import com.rbc.basketprice.exception.BasketItemNotFoundException;
import com.rbc.basketprice.exception.InvalidBasketItemException;
import com.rbc.basketprice.exception.InvalidProductException;
import com.rbc.basketprice.services.ifaces.BasketService;
import com.rbc.productstatic.domain.Product;
import com.rbc.productstatic.enums.UnitType;
import com.rbc.productstatic.services.ifaces.ProductService;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BasketServiceImplTest {

    private final ProductService productService = Mockito.mock(ProductService.class);
    private final BasketService basketService = new BasketServiceImpl(productService);

    @Test
    public void checkBucketAddsUpCorrectlyTest() {

        final int lemonId = 1;
        final BigDecimal lemonQtyInBasket = BigDecimal.TEN;
        final BigDecimal lemonPrice = new BigDecimal("0.50");
        final BigDecimal lemonUnitQty = BigDecimal.ONE;
        final Product productLemon = new Product(lemonId, "LEMONS", lemonPrice, lemonUnitQty, UnitType.BY_UNIT);

        final int orangeId = 2;
        final BigDecimal orangeQtyInBasket = new BigDecimal("150");
        final BigDecimal orangePrice = new BigDecimal("0.80");
        final BigDecimal orangeUnitQty = new BigDecimal("100");
        final Product productOrange = new Product(orangeId, "ORANGES", orangePrice, orangeUnitQty, UnitType.BY_WEIGHT);

        final int bananaId = 3;
        final BigDecimal bananaQtyInBasket =  new BigDecimal("200");
        final BigDecimal bananaPrice = new BigDecimal("1.75");
        final BigDecimal bananaUnitQty = new BigDecimal("100");
        final Product productBanana = new Product(bananaId, "BANANAS", bananaPrice, bananaUnitQty, UnitType.BY_WEIGHT);

        when(productService.getProduct(lemonId)).thenReturn(productLemon);
        when(productService.getProduct(orangeId)).thenReturn(productOrange);
        when(productService.getProduct(bananaId)).thenReturn(productBanana);

        Basket basket = new Basket();
        basket.addItem(new BasketItem(lemonId, lemonQtyInBasket));
        basket.addItem(new BasketItem(orangeId, orangeQtyInBasket));
        basket.addItem(new BasketItem(bananaId, bananaQtyInBasket));

        BigDecimal totalCost = basketService.findBasketCost(basket);

        assertEquals(new BigDecimal("9.70"),totalCost);

    }

    @Test(expected=BasketItemNotFoundException.class)
    public void checkBasketItemNotFoundExceptionIsThrownTest() {

        Basket basket = new Basket();
        basket.addItem(new BasketItem(999, BigDecimal.ONE)); // invalid product id.

        basketService.findBasketCost(basket);

    }

    @Test(expected=InvalidBasketItemException.class)
    public void checkInvalidBasketItemExceptionIsThrownWhenNullItemTest() {

        Basket basket = new Basket();
        basket.addItem(null);               // Null item.

        basketService.findBasketCost(basket);

    }

    @Test(expected=InvalidBasketItemException.class)
    public void checkInvalidBasketItemExceptionIsThrownWhenQtyNegativeTest() {

        Basket basket = new Basket();
        basket.addItem(new BasketItem(1, new BigDecimal("-1"))); // Negative Quantity.

        basketService.findBasketCost(basket);

    }

    @Test(expected=InvalidProductException.class)
    public void checkInvalidProductExceptionIsThrownWhenQtyNegativeTest() {

        Basket basket = new Basket();
        basket.addItem(new BasketItem(1, BigDecimal.ONE));
        final BigDecimal price = new BigDecimal("1.50");

        final Product product = new Product(1, "TEST", price, new BigDecimal("-1"), UnitType.BY_UNIT); // Negative Qty
        when(productService.getProduct(1)).thenReturn(product);

        basketService.findBasketCost(basket);

    }

    @Test(expected=InvalidProductException.class)
    public void checkInvalidProductExceptionIsThrownWhenPriceNegativeTest() {

        Basket basket = new Basket();
        basket.addItem(new BasketItem(1, BigDecimal.ONE));
        final BigDecimal price = new BigDecimal("-1.50");

        final Product product = new Product(1, "TEST", price, BigDecimal.ONE, UnitType.BY_UNIT); // Negative Price
        when(productService.getProduct(1)).thenReturn(product);

        basketService.findBasketCost(basket);

    }

}
