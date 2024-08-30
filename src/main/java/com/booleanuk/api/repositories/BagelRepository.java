package com.booleanuk.api.repositories;

import com.booleanuk.api.models.Bagel;

import java.util.ArrayList;
import java.util.List;

public class BagelRepository {
    private List<Bagel> bagels = new ArrayList<>();

    public void create(String name, int price) {
        Bagel bagel = new Bagel(name, price);
        this.bagels.add(bagel);
    }

    public List<Bagel> findAll() {
        return this.bagels;
    }

    public Bagel find(int id) {
        return this.bagels.stream()
                .filter(bagel -> bagel.getId() == id)
                .findFirst()
                .orElseThrow();
    }
}
