/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktv21marketkrylov;

import java.util.Scanner;
import entity.Customer;
import entity.Product;
import entity.Orders;
import java.util.List;
import managers.BaseManager;
import managers.CustomerManager;
import managers.DataManager;
import managers.ProductManager;
import managers.PurchaseManager;
import managers.interfaces.SaveManagerInterface;

/**
 *
 * @author pupil
 */
public class App {
    public static boolean saveToBase;
    private final Scanner scanner;
    private final CustomerManager customerManager;
    private final PurchaseManager purchaseManager;
    private final ProductManager productManager;
    
    private final SaveManagerInterface saveManager;
   
    private List<Orders>purchases;
    private List<Customer>customers;
    private List<Product>products;
    

    public App() {
        
        scanner = new Scanner(System.in);
        customerManager = new CustomerManager();
        purchaseManager = new PurchaseManager();
        productManager = new ProductManager();
        
        if(App.saveToBase){
            saveManager = new BaseManager();
        }else{
            saveManager = new DataManager();
        }
        
        purchases = saveManager.loadPurchases();
        customers = saveManager.loadCustomers();
        products = saveManager.loadProducts();
    }
    
    public void run(){
        String splitter = "\n------------------------------------------------------------------------------";
        boolean repeat = true;
        do{
            System.out.println("                        APP OPTIONS:");
            System.out.println("0 - Quit");
            System.out.println("1 - Add product     |   2 - Products list    |" );
            System.out.println("3 - Add customer    |   4 - Customers list   |    5 - Manage wallets");
            System.out.println("6 - Make shopping   |   7 - Orders history");
            System.out.print("Choose options number: ");
            
            int task = scanner.nextInt();
            scanner.nextLine();
            switch (task){
                case 0:
                    repeat = false;
                    break;
                case 1:
                    System.out.println("1 - Add product");
                    products.add(productManager.createProduct());
                    saveManager.saveProducts(products);
                    System.out.println(splitter);
                    break;
                case 2:
                    System.out.println("2 - Products list");
                    productManager.printListProducts(products);
                    System.out.println(splitter);
                    break;
                case 3:
                    System.out.println("3 - Add customer");
                    customers.add(customerManager.createCustomer());
                    saveManager.saveCustomers(customers);
                    System.out.println(splitter);
                    break;
                case 4:
                    System.out.println(" 4 - Customers list");
                    customerManager.printListCustomers(customers);
                    System.out.println(splitter);
                    break;
                case 5:
                    System.out.println("5 - Manage wallets");
                    customerManager.addBalance(customers);
                    saveManager.saveCustomers(customers);
                    System.out.println(splitter);
                    break;
                case 6:
                    System.out.println("6 - Make shopping");
                    purchases.add(purchaseManager.createPurchase(products, customers));
                    saveManager.savePurchases(purchases);
                    saveManager.saveCustomers(customers);
                    saveManager.saveProducts(products);
                    System.out.println(splitter);
                    break;
                case 7:
                    System.out.println("7 - Orders history");
                    purchaseManager.shopMoney(purchases);
                    System.out.println(splitter);
                    break;
                default:
                    System.out.println("There is no function with such number\nChoose function number from the list!\n---------------------------------------------------------------------------");
            }
        }while(repeat);
        System.out.println("GOOD BYE!");
    }
    
   

    
}
