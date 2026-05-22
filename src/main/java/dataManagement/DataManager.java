package dataManagement;

import applicationCore.ProductType;

import java.util.Arrays;
import java.util.function.Predicate;

public class DataManager {
    private static Product[] products = new Product[13];

    public static void loadProducts() {
        products[0] = new Product("Espresso", 2.00);
        products[1] = new Product("Latte Macchiato", 3.50);
        products[2] = new Product("Cappuccino", 3.00);
        products[3] = new Product("English Breakfast", 2.50);
        products[4] = new Product("Chai Tea", 2.50);
        products[5] = new Product("Green Tea", 2.00);
        products[6] = new Product("Croissant", 1.00);
        products[7] = new Product("Schokobrötchen", 2.00);
        products[8] = new Product("Cinnamon Roll", 5.00);
        products[9] = new Product("Plain", 0.00, ProductType.EXTRA);
        products[10] = new Product("Chocolate", 0.50, ProductType.EXTRA);
        products[11] = new Product("Hazelnut", 0.50, ProductType.EXTRA);
        products[12] = new Product("Vanilla", 0.50, ProductType.EXTRA);
    }

    public static Product[] getProducts(){
        return products.clone();
    }


    private static Product[] filterProducts(ProductType type){
        // Must-use stream for simplicity

        // Theoretically, this does not exist since all array slots are filled. But since no defensive checks are being made elsewhere, this is a good place to be defensive.
        Predicate<Product> isNotNull = p -> p != null;

        Predicate<Product> isXTypeProduct = p -> p.getType() == type;

        return Arrays.stream(products).filter(isNotNull).filter(isXTypeProduct).toArray(Product[]::new);
    }

    public static Product[] getBaseProducts(){
        return filterProducts(ProductType.BASE);
    }

    public static Product[] getExtraProducts(){
        return filterProducts(ProductType.EXTRA);
    }


    // Will return null if the loadProducts() was not called to populate the array.
    // Addressing this possible case is skipped :)
    public static Product getProduct(int id){
        for (Product product : products){
            if(product.getId() == id){
                return product;
            }
        }

        // bad practice, an error should be thrown. I just want a PoC :)
        // Best practices implementation can be done later
        return null;
    }
}
