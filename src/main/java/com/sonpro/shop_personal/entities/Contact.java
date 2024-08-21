package com.sonpro.shop_personal.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter @Setter
@Entity @Table(name = "contacts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contact {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String phone;
    String address;
    String website;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    Account account;

}
