package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

    private ArrayList<Item> items;

    public ItemRepository(){
        this.items = new ArrayList<>();
        items.add(new Item("bible", "book", 300));
        items.add(new Item("Bitcoin whitepaper", "whitepaper", 0));
        items.add(new Item("gulag archipelico", "Book", 30));
        items.add(new Item("コンビ二人間", "本", 5));
    }

    public List<Item> getAll(String category) {
        if (category== null)
            return this.items;

        var returnItems = this.items.stream().filter(
                item -> item.getCategory().equals(category)
        ).toList();
        if (returnItems.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");

        return returnItems;
    }

    public Item getOne(int id) {
        return this.items.stream().filter(
                item -> item.getId() == id
        ).findFirst().orElseThrow( ()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.")
        );
    }

    public Item remove(int id) {
        Item item = getOne(id);
        this.items.remove(item);
        return item;
    }

    public Item add(Item item) {
        if (this.items.stream()
                .anyMatch(a -> a.getName().equals(item.getName()))
        ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Product with provided name already exists.");
        }
        this.items.add(item);
        return item;
    }

    public Item update(int id, Item item) {
        Item toUpdate = getOne(id);
        if (this.items.stream()
                .anyMatch(a -> a.getName().equals(item.getName()))
        ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Product with provided name already exists.");
        }

        toUpdate.setName(item.getName());
        toUpdate.setCategory(item.getCategory());
        toUpdate.setPrice(item.getPrice());
        return toUpdate;
    }
}
