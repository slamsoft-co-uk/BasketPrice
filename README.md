# BasketPrice
Basket Pricing Application
==========================

This project has been written in Java 8 using IntelliJ Idea.  The project is Maven based.

The following brief was provided:-

---------------------------------------------------------------------------------------------------------
Write a program that takes a basket of items and outputs its total cost.

The basket can contain one or more of the following items: Bananas, Oranges, Apples, Lemons, Peaches
---------------------------------------------------------------------------------------------------------


The key method that calculates the total cost of the basket is:-

*  public BigDecimal com.rbc.basketprice.services.impl.BasketServiceImpl.findBasketCost(Basket basket)


A number of unit tests have been written but the key one that tests the above method is:-

*  com.rbc.basketprice.services.impl.checkBucketAddsUpCorrectlyTest

This test provides a basket of the following fruits:-

* 10 Lemons @ 50p per lemon
* 150g of Oranges @ 80p per 100g
* 200g of Bananas @ £1.75 per 100g


A separate product-static module has been created which is simply a mocked interface that returns made-up prices and quantities for each given fruit item.  It is envisaged that in a production system a separate process such as a web-service would provide the prices/quantities for the items in question.

This approach keeps the BasketPrice module completely independant of however the static product information is to be derived.  It also allows the BasketPrice module to be stateless so that there is less concern about running multiple instances of the BasketService process.

Aside from the "fruit" data in the unit tests, the class names have been named with reasonably generic names to allow different item types in the future (something other than fruit).  As long as the products have a name, unit quantity, price and unit-type (ie; by weight or by unit) then the item can be anything you like.


Steve Lamb, May 11 2016.

