package products;

import java.util.ArrayList;
import java.util.List;

//Store data, and create methods to retrieve it
public class ProductRepository {

    private ArrayList<Product> products;
    public ProductRepository(){
        this.products = new ArrayList<>();
    }



    public List<Product> getAll(){
        return products;
    }

    public List<Product> getAll(String category){
        return products.stream().filter(product -> product.getCategory().equalsIgnoreCase(category)).toList();
    }

    public Product getOne(String name){
        for (Product product : products){
            if (product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }


    public Product getOne(int id){
        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);

    }
    public Product create(Product product){
        if (products.stream().noneMatch(product1 -> product1.getName().equals(product.getName()))){
            products.add(product);
            return product;
        }
        return null;
    }

    public Product delete(int id){
        Product product = products.stream().filter(product1 -> product1.getId() == id).findFirst().orElse(null);
        if (product != null){
            products.remove(product);
        }
        return product;
    }

    public Product update(int id, Product updProd){
        Product product = products.stream().filter(product1 -> product1.getId() == id).findFirst().orElse(null);
        if (product != null){
            product.setCategory(updProd.getCategory());
            product.setPrice(updProd.getPrice());
            product.setName(updProd.getName());
        }
        return product;
    }


}
