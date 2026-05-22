package applicationCore;

public class Billing implements Billable {
    private Orderable order = null;
    private double billAmount = 0;

    public Billing(Orderable order){
        this.order = order;
    }

    @Override
    public double calculateBill(Orderable o) {
        double baseBill = 0;
        double costOfExtras = 0;

        for (Purchasable p : o.getItems()){
            Purchasable[] extras = p.getExtras();

            for (Purchasable e : extras){
                costOfExtras = costOfExtras + e.getPrice();
            }

            baseBill = baseBill + p.getPrice();
        }

        return baseBill + costOfExtras;

    }
}
