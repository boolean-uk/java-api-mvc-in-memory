package com.booleanuk.api.bagels.repositories;
import com.booleanuk.api.bagels.model.Bagel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

public class BagelRepository {
    private int idCounter = 1;
    private List<Bagel> data ;
    public BagelRepository() {
        this.data = new ArrayList<>();
    }

    public Bagel create(String type, int price) {
        Bagel bagel = new Bagel(this.idCounter++, type, price);
        boolean created = this.data.add(bagel);
        if (created) {
            return bagel;
        }
        return null;
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
    public Bagel update(int id, Bagel updatedBagel) {
        Bagel bagelToUpdate = this.data.stream()
                .filter(bagel -> bagel.getId() == id)
                .findFirst()
                .orElse(null);
        if (bagelToUpdate != null) {
            bagelToUpdate.setType(updatedBagel.getType());
            bagelToUpdate.setPrice(bagelToUpdate.getPrice());
        }
        bagelToUpdate = this.find(id);
        return bagelToUpdate;
    }
    public Bagel delete(int id) {
        Bagel bagelToDelete = this.find(id);
        if (bagelToDelete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No bagel found");
        }
        return  bagelToDelete;
    }
}
