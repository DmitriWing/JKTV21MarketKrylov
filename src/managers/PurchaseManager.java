/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Customer;
import entity.Product;
import entity.Orders;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pupil
 */
public class PurchaseManager {
    private final Scanner scanner;
    private final ProductManager productManager;
    private final CustomerManager customerManager;

    public PurchaseManager() {
        scanner = new Scanner(System.in);
        productManager = new ProductManager();
        customerManager = new CustomerManager();
    }

    public Orders createPurchase(List<Product> products, List<Customer> customers) {
        
        System.out.println("Customers list: ");
        customerManager.printListCustomers(customers);
        System.out.println("Choose customer's number: ");
        int numberCustomer = scanner.nextInt(); scanner.nextLine();
        
        System.out.println("Products list: ");
        productManager.printListProducts(products);
        System.out.println("Choose product number: ");
        int numberProduct = scanner.nextInt(); scanner.nextLine();
        
        System.out.println("How many items would you like? ");
        int countProduct = scanner.nextInt(); scanner.nextLine();
        
        Orders purchase = new Orders();
        purchase.setCustomer(customers.get(numberCustomer - 1));
        purchase.setTakeOfProduct(new GregorianCalendar().getTime());
        purchase.setProduct(products.get(numberProduct - 1));
        purchase.setAmountCustomer(countProduct);
        purchase.setPriceCustomer(products.get(numberProduct - 1).getPrice());
        products.get(numberProduct - 1).setAmountShop(products.get(numberProduct - 1).getAmountShop() - countProduct);
        customers.get(numberCustomer - 1).setMoney(customers.get(numberCustomer - 1).getMoney() - countProduct * products.get(numberProduct - 1).getPrice());
        
        return purchase;
    }
    
     public void shopMoney(List<Orders>purchases) {
        int shopMoney = 0; 
        
        for (int i = 0; i < purchases.size(); i++) {
            // беру цену из purchases
            shopMoney = shopMoney + purchases.get(i).getAmountCustomer() * purchases.get(i).getPriceCustomer();
            // беру цену из product
//            shopMoney = shopMoney + purchases.get(i).getAmountCustomer() * purchases.get(i).getProduct().getPrice();
            
        }
        System.out.printf("%nTotal turnover: %d eur%n",shopMoney);
        System.out.println();
         System.out.println(purchases);
        Arrays.toString(purchases.toArray());
    }
}
