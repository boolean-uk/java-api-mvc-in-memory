package com.booleanuk.api.bagels.controllers;

import com.booleanuk.api.bagels.repositories.BagelRepository;
import com.booleanuk.api.bagels.models.Bagel;

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
