package applicationCore;

public interface Purchasable {
    double getPrice();
    String getName();
    ProductType getType();
    int getId();
    void addExtras(Purchasable p);
    Purchasable[] getExtras();
}
