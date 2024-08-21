package com.sonpro.shop_personal.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@Getter @Setter
@Entity @Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String description;
    String image;
    String name;
    BigDecimal price;
    Float weight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category category;
}
