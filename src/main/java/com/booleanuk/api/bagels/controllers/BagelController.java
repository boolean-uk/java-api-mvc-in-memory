package com.booleanuk.api.bagels.controllers;
import com.booleanuk.api.bagels.model.Bagel;
import com.booleanuk.api.bagels.repositories.BagelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("bagels")
public class BagelController {
    private BagelRepository repository;

    public BagelController() {
        this.repository = new BagelRepository();
    }

    public List<Bagel> getAll() {
        return this.repository.findAll();
    }
    @GetMapping("/{id}")
    public Bagel getOne(@PathVariable int id) {
        Bagel bagel = this.repository.find(id);
        if (bagel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no bagel with this id");
        }
        return bagel;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bagel createBagel(@RequestBody Bagel bagel) {
        if (bagel.getType() == null || bagel.getPrice() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Type can't be null, price can't be 0");
        }
        return this.repository.create(bagel.getType(), bagel.getPrice());
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Bagel updateBagel(@PathVariable int id, @RequestBody Bagel bagel) {
        return  this.repository.update(id, bagel);
    }
    @DeleteMapping("/{id}")
    public Bagel deleteBagel(@PathVariable int id) {
        return this.repository.delete(id);
    }
}
