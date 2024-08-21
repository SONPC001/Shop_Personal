package com.sonpro.shop_personal.global;

import com.sonpro.shop_personal.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> Cart;

    static {
        Cart = new ArrayList<>();
    }
}
