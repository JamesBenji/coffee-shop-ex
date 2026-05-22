package dataManagement;

import applicationCore.ProductType;
import applicationCore.Purchasable;

import java.util.Arrays;
import java.util.function.Predicate;

public class Product implements Purchasable {
    static int count = 1;
    private int id = 0;
    private String name = "";
    private ProductType type = ProductType.BASE;
    private double price = 0.0d;

    // quantity state ignored. It is determined by the order made, thus is not a characteristic of a product.

    // manual limit to flavours. Avoided the List structure though this would be a good use case
    private Product[] extras = new Product[10];
    private int extrasCounter = 0;

    public Product(String name, double price) {
        this(name, price, ProductType.BASE);
    }

    public Product(String name, double price, ProductType type) {
        this.id = count++;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ProductType getType() {
        return this.type;
    }

    @Override
    public int getId(){
        return this.id;
    }

    @Override
    public void addExtras(Purchasable p) {
        // check for extrasCounter < 11 ignored :)
        // Casting from interface. I find this is safe
        this.extras[extrasCounter++] = (Product) p;
    }

    @Override
    public Product[] getExtras() {
        Predicate<Purchasable> isNotNull = p -> p != null;

        return Arrays.stream(this.extras).filter(isNotNull).toArray(Product[]::new);
    }
}
