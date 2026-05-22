package userInteractions;

import applicationCore.*;
import dataManagement.DataManager;

import java.util.Scanner;

public class UserInteractions {
    private static int selectedItemNumber = 0;
    private static boolean hasLoadedProducts = false;
    private static final Scanner input = new Scanner(System.in);

    public static void displayStartBanner(){
        System.out.println("Cafecito Lindo");
        System.out.println("\tWELCOME");
        System.out.println();
        System.out.println("Use Ctrl + C to end the program");
    }

    public static void displayProductsMenu(Purchasable[] purchasables){
        System.out.println();
        System.out.println("Cafecito Lindo Menu");
        System.out.println();
        for (Purchasable p : purchasables) {
            System.out.printf("%-3d %-25s %5.2f €", p.getId(), p.getName(), p.getPrice());
            System.out.println();
        }
    }

    public static void displayExtrasMenu(Purchasable[] purchasables){
        System.out.println();
        System.out.println("Flavours");
        System.out.println();
        for (Purchasable p : purchasables) {
            System.out.printf("%-3d %-25s %5.2f €", p.getId(), p.getName(), p.getPrice());
            System.out.println();
        }
    }

    public static void displayUserInputPrompt(){
        System.out.println();
        System.out.println("Enter your selection");
    }

    public static int acceptProductMenuSelection(Purchasable[] items) throws SelectionOutOfRangeException{
        displayUserInputPrompt();
        try{
            selectedItemNumber = input.nextInt();
            Purchasable lastItem = items[items.length - 1];

            if (selectedItemNumber < items[0].getId() || selectedItemNumber > lastItem.getId()) {
                throw new SelectionOutOfRangeException("Err: Selection out of range. Expected integer from " + items[0].getId() + " to " + lastItem.getId());
            }

            return selectedItemNumber;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void displayBilling(Orderable o){
        Billable bill = new Billing(o);
        double orderBilling = bill.calculateBill(o);

        System.out.println();
        System.out.printf("Your bill is %.2f €", orderBilling);
    }

    public static void reset(){
        selectedItemNumber = 0;
        run();
    }

    public static void run(){
        if (!hasLoadedProducts) {
            UserInteractions.displayStartBanner();
            DataManager.loadProducts();
            hasLoadedProducts = true;
        }

        UserInteractions.displayProductsMenu(DataManager.getBaseProducts());

        try {
            int selectedProductId = UserInteractions.acceptProductMenuSelection(DataManager.getBaseProducts());

            Orderable order = new Order();
            order.addToCart(DataManager.getProduct(selectedProductId));

            UserInteractions.displayExtrasMenu(DataManager.getExtraProducts());

            int selectedExtraId = UserInteractions.acceptProductMenuSelection(DataManager.getExtraProducts());
            order.addExtras(selectedProductId, DataManager.getProduct(selectedExtraId));

            UserInteractions.displayBilling(order);

        } catch (SelectionOutOfRangeException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
