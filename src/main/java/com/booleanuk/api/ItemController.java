package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products")
public class ItemController {
    private ItemRepository itemRepository;

    public ItemController() {
        this.itemRepository = new ItemRepository();
    }

    @GetMapping
    public List<Item> getAll(@RequestParam(required = false) String category) {
        return this.itemRepository.getAll(category);
    }

    @GetMapping("/{id}")
    public Item getOne(@PathVariable int id) {
        return this.itemRepository.getOne(id);
    }

    @DeleteMapping("/{id}")
    public Item deleteOne(@PathVariable int id) {
        return this.itemRepository.remove(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item addItem(@RequestBody Item item) {
        this.itemRepository.add(item);
        return item;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Item update(@PathVariable int id,@RequestBody Item item) {
        return this.itemRepository.update(id, item);
    }
}
