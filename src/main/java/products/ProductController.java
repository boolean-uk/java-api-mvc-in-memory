package products;

//Handle spring code

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepository;


    public ProductController() {
        this.productRepository = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        if (product.getPrice() <= 0 || product.getCategory() == null || product.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
        }
        if (productRepository.create(product) != null) {
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getOneProduct(@PathVariable int id) {
        Product product = productRepository.getOne(id);
        if (product != null) {
            return product;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product already exists");
        }
    }


    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Product> getAll(@RequestParam(required = false) String category) {
        List<Product> productList;
        if (category == null) {
            return productRepository.getAll("");
        } else {
            productList = productRepository.getAll(category.toLowerCase());
            if (productList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            } else {
                return productList;
            }
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product delete(@PathVariable(name = "id") int id) {
        Product product = productRepository.delete(id);
        if (product != null) {
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@RequestBody Product prod, @PathVariable(name = "id") int id) {
        if (productRepository.getAll().stream().anyMatch(product1 -> product1.getName().equalsIgnoreCase(prod.getName()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
        }
        Product p = productRepository.update(id, prod);
        if (p != null) {
            return p;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
    }


}
