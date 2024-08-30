package com.booleanuk.api.products;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
  private final ProductRepository repository = new ProductRepository();

  @PostMapping
  public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO) throws ResponseStatusException {
    return new ResponseEntity<>(this.repository.create(productDTO),
        HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Product>> getCategoryOrAll(@RequestParam(required = false) Optional<String> maybeCategory)
      throws ResponseStatusException {
    return maybeCategory
        .map(category -> new ResponseEntity<>(this.repository.getCategory(category.toLowerCase()), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(this.repository.getAll(), HttpStatus.OK));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Product> getById(@PathVariable int id) throws ResponseStatusException {
    return new ResponseEntity<>(this.repository.getById(id), HttpStatus.OK);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Product> updateById(@PathVariable int id, @RequestBody ProductDTO productDTO)
      throws ResponseStatusException {
    return new ResponseEntity<>(this.repository.updateById(id, productDTO), HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Product> removeById(@PathVariable int id) throws ResponseStatusException {
    return new ResponseEntity<>(this.repository.removeById(id), HttpStatus.OK);
  }

  // Not part of the assignment
  @GetMapping(value = "/418")
  public ResponseEntity<String> getTeapot() {
    return new ResponseEntity<>(this.repository.getTeapotTag(), HttpStatus.I_AM_A_TEAPOT);
  }
}
