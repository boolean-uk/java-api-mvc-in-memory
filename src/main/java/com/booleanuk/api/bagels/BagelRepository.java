package com.booleanuk.api.bagels;

import com.booleanuk.api.bagels.Bagel;

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
    public Bagel delete(int id){
        for(Bagel bagel: this.data){
            if (bagel.getId()==id){
                data.remove(bagel);
                return bagel;
            }
        }
        return null;
    }
    public Bagel update(int id, Bagel newBagel){
        for(Bagel bagel: this.data){
            if (bagel.getId()==id){
                data.remove(bagel);
                return bagel;
            }
        }
        return null;
    }
}
