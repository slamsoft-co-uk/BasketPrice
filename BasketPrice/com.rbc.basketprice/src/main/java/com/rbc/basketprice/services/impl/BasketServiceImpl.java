package com.rbc.basketprice.services.impl;

import com.rbc.basketprice.domain.Basket;
import com.rbc.basketprice.domain.BasketItem;
import com.rbc.basketprice.exception.InvalidBasketItemException;
import com.rbc.basketprice.exception.BasketItemNotFoundException;
import com.rbc.basketprice.exception.InvalidProductException;
import com.rbc.basketprice.services.ifaces.BasketService;
import com.rbc.productstatic.domain.Product;
import com.rbc.productstatic.services.ifaces.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class BasketServiceImpl implements BasketService{

    private static final Logger LOGGER = LoggerFactory.getLogger(BasketServiceImpl.class);

    private final ProductService productService;

    public BasketServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    public BigDecimal findBasketCost(Basket basket) {

        if (basket == null) {
            throw new IllegalArgumentException("No basket has been supplied");
        }

        List<BasketItem> basketItems = basket.getBasketItems();

        if (basketItems == null || basketItems.size() == 0) {
            throw new IllegalArgumentException("No items have been supplied in your basket");
        }

        BigDecimal totalCost = BigDecimal.ZERO;

        for (BasketItem basketItem : basketItems) {
            validateBasketItem(basketItem);
            Product product = productService.getProduct(basketItem.getId());
            if (product == null) {
                throw new BasketItemNotFoundException("Product Id: " + basketItem.getId() + " not found");
            }
            BigDecimal itemPrice = getItemPrice(product, basketItem);
            totalCost = totalCost.add(itemPrice);
        }

        LOGGER.info("Items processed: " + basketItems.size() + ", total cost: " + totalCost);

        return totalCost;

    }

    private void validateBasketItem(BasketItem basketItem) {

        if (basketItem == null) {
            throw new InvalidBasketItemException("Basket item is null");
        }

        if (basketItem.getRequiredQuantity().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidBasketItemException("Required quantity less than or equal to zero");
        }

    }

    private BigDecimal getItemPrice(Product product, BasketItem basketItem) {

        // Check product details are okay.

        if (product.getUnitQuantity().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidProductException("Required unit quantity less than or equal to zero");
        }

        if (product.getCostPerUnit().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidProductException("Required cost per unit less than or equal to zero");
        }

        BigDecimal itemPrice = product.getCostPerUnit().multiply(basketItem.getRequiredQuantity()).divide(product.getUnitQuantity(), RoundingMode.CEILING);

        LOGGER.info("Item being processed: " + product.getProductName() +
                    ", Quantity required: " + basketItem.getRequiredQuantity() +
                    ", Unit Price: " + product.getCostPerUnit() +
                    ", Unit Quantity: " + product.getUnitQuantity() +
                    ", Sold: " + product.getUnitType().getName() +
                    ", Item Price: <" + itemPrice + ">");

        return itemPrice;

    }

}