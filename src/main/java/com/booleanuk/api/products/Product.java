package com.booleanuk.api.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Product {
    private int id;
    private @Setter String name;
    private @Setter String category;
    private @Setter int price;
}
