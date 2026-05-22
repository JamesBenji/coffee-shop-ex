package applicationCore;

import java.util.Arrays;

public class Order implements Orderable{

    // Forced limit: An order can only have 10 items
    // Why: Avoiding the use of Lists
    private Purchasable[] cart = new Purchasable[10];
    private int cartCounter = 0;
    private int extrasCounter = 0;

    public Order(){

    }
    @Override
    public void addToCart(Purchasable p) {
        // checking for count over 10 ignored :)
        cart[cartCounter++] = p;
    }

    @Override
    public void addExtras(int basePurchasableId, Purchasable extraPurchasable) {
        Purchasable p = null;

        for (Purchasable item : cart){
            // A null check because the cart array was initialized with nulls. A list would have worked better
            if(item != null && item.getId() == basePurchasableId) {
                p = item;
            }
        }

        p.addExtras(extraPurchasable);
    }

    @Override
    public Purchasable[] getItems() {
        return Arrays.stream(this.cart).filter(p -> p != null).toArray(Purchasable[]::new);
    }
}
