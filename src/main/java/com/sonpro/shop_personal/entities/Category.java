package com.sonpro.shop_personal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categorys")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    List<Product> products;
}
