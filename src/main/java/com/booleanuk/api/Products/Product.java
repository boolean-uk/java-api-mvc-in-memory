package com.booleanuk.api.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class Product {
    private int id;
    private String name;
    private String category;
    private int price;

}
