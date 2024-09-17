package com.booleanuk.api.bagels;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bagels")
public class BagelController {
    private BagelRepository repository;

    public BagelController() {
        repository = new BagelRepository();
    }

    @GetMapping
    public List<Bagel> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Bagel findOneBagel(@PathVariable int id) {
        return repository.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bagel createBagel(@RequestBody Bagel bagel) {
        return repository.create(bagel.getType(), bagel.getPrice());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Bagel updateBagel(@PathVariable int id, @RequestBody Bagel bagel) {
        return repository.update(id, bagel.getType(), bagel.getPrice());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Bagel deleteBagel(@PathVariable int id) {
        return repository.delete(id);
    }
}
