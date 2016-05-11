package com.rbc.basketprice.services.ifaces;

import com.rbc.basketprice.domain.Basket;
import java.math.BigDecimal;

public interface BasketService {

    BigDecimal findBasketCost(Basket basket);

}
