package com.booleanuk.api.controller;

import com.booleanuk.api.repository.BagelRepository;
import com.booleanuk.api.model.Bagel;

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
