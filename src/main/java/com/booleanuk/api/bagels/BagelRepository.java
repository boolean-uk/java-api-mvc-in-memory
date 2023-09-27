package com.booleanuk.api.bagels;

import java.util.ArrayList;
import java.util.List;

public class BagelRepository {
    private int idCounter = 1;
    private List<Bagel> data = new ArrayList<>();

    public Bagel create(String type, int price) {
        Bagel bagel = new Bagel(this.idCounter++, type, price);
        this.data.add(bagel);
        return bagel;
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

    public Bagel update(int id, String type, int price) {
        for (Bagel bgl : data) {
            if (bgl.getId() == id) {
                bgl.setType(type);
                bgl.setPrice(price);
                return bgl;
            }
        }
        return null;
    }

    public Bagel delete(int id) {
        Bagel bagel = null;

        for (Bagel bgl : data) {
            if (bgl.getId() == id) {
                bagel = bgl;
            }
        }

        if (bagel != null) {
            this.data.remove(bagel);
        }
        return null;
    }
}
