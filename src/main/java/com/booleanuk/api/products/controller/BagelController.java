package com.booleanuk.api.products.controller;

import com.booleanuk.api.products.model.BagelRepository;
import com.booleanuk.api.products.model.Bagel;

import java.util.List;

public class BagelController {
    BagelRepository repository;

    public BagelController(BagelRepository repository) {
        this.repository = repository;
    }

    public List<Bagel> getAll() {
        return this.repository.findAll();
    }
}
