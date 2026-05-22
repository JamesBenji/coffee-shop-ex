package applicationCore;

public interface Orderable {
    void addToCart(Purchasable purchasable);
    void addExtras(int basePurchasableId, Purchasable extraPurchasable);
    Purchasable[] getItems();
}
