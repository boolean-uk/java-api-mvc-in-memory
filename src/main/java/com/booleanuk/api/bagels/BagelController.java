package com.booleanuk.api.bagels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/bagel")
public class BagelController {

    @Autowired
    BagelRepository repository;

    @GetMapping
    public List<Bagel> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public Bagel findOneBagel(@PathVariable int id) {
        return this.repository.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bagel createBagel(@RequestBody Bagel bagel) {
        return this.repository.create(bagel.getType(), bagel.getPrice());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Bagel updateBagel(@PathVariable int id, @RequestBody Bagel bagel) {
        return this.repository.update(id, bagel.getType(), bagel.getPrice());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Bagel deleteBagel(@PathVariable int id) {
        return this.repository.delete(id);
    }
}
