package products;


//Define object product in this class
public class Product {

    private String name;
    private String category;
    private int price;

    private int id;

    private int nextId = 0;

    public Product(String name, String category, int price){
        this.name = name;
        this.category = category;
        this.price = price;
        this.id = nextId++;
    }



    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }
}
