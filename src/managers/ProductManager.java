/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Product;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pupil
 */
public class ProductManager {
    private final Scanner scanner;

    public ProductManager() {
        scanner = new Scanner(System.in);
    }

    public Product createProduct() {
        Product product = new Product();
        System.out.println("Введите наименование продукта: ");
        product.setTitle(scanner.nextLine());
        System.out.println("Вводите производителя: ");
        product.setProducer(scanner.nextLine());
        System.out.println("Введите количество: ");
        product.setAmountShop(scanner.nextInt());
        System.out.println("Введите цену: ");
        product.setPrice(scanner.nextInt());
        return product;
    }
    
    public void printListProducts(List<Product> products ){
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%d. %s Производитель: %s. Количество: %d Цена: %d%n",
                    i+1,
                    products.get(i).getTitle(),
                    products.get(i).getProducer(),
                    products.get(i).getAmountShop(),
                    products.get(i).getPrice()
            );
        }
    }
    
}
