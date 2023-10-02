package com.booleanuk.api.bagels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BagelRepository {
    private int idCounter = 1;
    private Map<Integer, Bagel> bagels = new HashMap<>();

    public Bagel create(String type, int price) {
        Bagel bagel = new Bagel(idCounter, type, price);
        bagels.put(idCounter, bagel);
        idCounter++;
        return bagel;
    }

    public List<Bagel> findAll() {
        return new ArrayList<>(bagels.values());
    }

    public Bagel find(int id) {
        return bagels.get(id);
    }

    public Bagel update(int id, String type, int price) {
        Bagel bagel = bagels.get(id);
        if (bagel != null) {
            bagel.setType(type);
            bagel.setPrice(price);
            return bagel;
        }
        return null;
    }

    public Bagel delete(int id) {
        return bagels.remove(id);
    }
}
