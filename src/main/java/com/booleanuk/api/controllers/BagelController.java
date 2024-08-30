package com.booleanuk.api.controllers;

import com.booleanuk.api.repositories.BagelRepository;
import com.booleanuk.api.models.Bagel;

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
