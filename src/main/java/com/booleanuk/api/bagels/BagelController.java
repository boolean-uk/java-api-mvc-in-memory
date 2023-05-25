package com.booleanuk.api.bagels;

import com.booleanuk.api.products.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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
