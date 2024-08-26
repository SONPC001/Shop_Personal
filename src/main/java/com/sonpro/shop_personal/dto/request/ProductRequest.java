package com.sonpro.shop_personal.dto.request;

import com.sonpro.shop_personal.entities.Category;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductRequest implements Serializable {
    Integer id;
    String description;
    String image;
    String name;
    Double price;
    Float weight;
    Category category;
}