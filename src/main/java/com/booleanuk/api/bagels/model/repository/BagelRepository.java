package com.booleanuk.api.bagels.model.repository;

import com.booleanuk.api.bagels.model.pojo.Bagel;

import java.util.ArrayList;
import java.util.List;

public class BagelRepository {
    private int idCounter = 1;
    private List<Bagel> data = new ArrayList<>();

    public void create(String type, int price) {
        Bagel bagel = new Bagel(this.idCounter++, type, price);
        this.data.add(bagel);
    }

    public List<Bagel> findAll() {
        return this.data;
    }

    public Bagel find(int id) {
        return this.data.stream()
                .filter(bagel -> bagel.getId() == id)
                .findFirst()
                .orElseThrow();
    }
}
