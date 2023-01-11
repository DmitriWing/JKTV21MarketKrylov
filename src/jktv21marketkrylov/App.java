/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktv21marketkrylov;

import java.util.Scanner;
import entity.Customer;
import entity.Product;
import entity.Purchase;
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
   
    private List<Purchase>purchases;
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
        boolean repeat = true;
        do{
            System.out.println("Функции приложения:");
            System.out.println("0. Выход из программы");
            System.out.println("1. Добавить продукт");
            System.out.println("2. Список продавемых продуктов");
            System.out.println("3. Добавить покупателя");
            System.out.println("4. Список зарегистрированных покупателей");
            System.out.println("5. Покупка покупателем продукта");
            System.out.println("6. Оборот магазина за все время работы");
            System.out.println("7. Добавить денег покупателю");
            System.out.print("Выберите номер функции: ");
            int task = scanner.nextInt();
            scanner.nextLine();
            switch (task){
                case 0:
                    repeat = false;
                    break;
                case 1:
                    System.out.println("1. Добавить продукт");
                    products.add(productManager.createProduct());
                    saveManager.saveProducts(products);
                    break;
                case 2:
                    System.out.println("2. Список продавемых продуктов");
                    productManager.printListProducts(products);
                    break;
                case 3:
                    System.out.println("3. Добавить покупателя");
                    customers.add(customerManager.createCustomer());
                    saveManager.saveCustomers(customers);
                    break;
                case 4:
                    System.out.println("4. Список зарегистрированных покупателей");
                    customerManager.printListCustomers(customers);
                    break;
                case 5:
                    System.out.println("5. Покупка покупателем продукта");
                    purchases.add(purchaseManager.createPurchase(products, customers));
                    saveManager.savePurchases(purchases);
                    saveManager.saveCustomers(customers);
                    saveManager.saveProducts(products);
                    break;
                case 6:
                    System.out.println("6. Оборот магазина за все время работы");
                    purchaseManager.shopMoney(purchases);
                    break;
                case 7:
                    System.out.println("7. Добавить денег покупателю");
                    customerManager.addBalance(customers);
                    saveManager.saveCustomers(customers);
                    break;
                default:
                    System.out.println("Выберите номер функции из списка!");
            }
        }while(repeat);
        System.out.println("Пока!");
    }
    
   

    
}
