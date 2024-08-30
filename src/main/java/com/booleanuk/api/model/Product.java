package com.booleanuk.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private static int idCounter = 1;

    private int id;
    private String name;
    private String category;
    private int price;

    @JsonCreator
    public Product(String name, String category, int price){
        this.id = idCounter++;
        this.name = name;
        this.category = category;
        this.price = price;
    }
}

