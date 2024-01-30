package com.booleanuk.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Product {
    private UUID id;
    private String name;
    private String category;
    private int price;
}
