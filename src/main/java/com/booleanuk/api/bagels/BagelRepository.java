package com.booleanuk.api.bagels;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//@RestController
//@RequestMapping("authors")
public class BagelRepository {
    private int idCounter = 1;
    private List<Bagel> data = new ArrayList<>();


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void create(@RequestBody String type,@RequestBody int price) {
//        Bagel bagel = new Bagel(this.idCounter++, type, price);
//        this.data.add(bagel);
//    }

//    public List<Bagel> findAll() {
//        return this.data;
//    }
//
//    @GetMapping("/{id}")
//    public Bagel find(int id) {
//        return this.data.stream()
//                .filter(bagel -> bagel.getId() == id)
//                .findFirst()
//                .orElseThrow();
//    }
}
