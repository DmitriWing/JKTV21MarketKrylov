/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Customer;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pupil
 */
public class CustomerManager {
    private final Scanner scanner;

    public CustomerManager() {
        scanner = new Scanner(System.in);
    }

    public Customer createCustomer() {
        Customer customer = new Customer();
        System.out.print("Введите имя покупателя: ");
        customer.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию покупателя: ");
        customer.setLastname(scanner.nextLine());
        System.out.print("Телефон: ");
        customer.setPhone(scanner.nextLine());
        System.out.println("Введите количество денег у покупателя: ");
        customer.setMoney(scanner.nextInt());
        return customer;
    }

    public void printListCustomers(List<Customer> customers) {
        for (int i = 0; i < customers.size(); i++) {
            System.out.printf("%d. %s  %s. Телефон: %s Деньги: %d%n",
                    i+1,
                    customers.get(i).getFirstname(),
                    customers.get(i).getLastname(),
                    customers.get(i).getPhone(),
                    customers.get(i).getMoney()
            );
        }
    }

    public void addBalance(List<Customer> customers) {
        System.out.println("Список покупателей: ");
        printListCustomers(customers);
        System.out.println("Выберите номер покупателя из списка: ");
        int numberCustomer = scanner.nextInt(); scanner.nextLine();
        
        System.out.printf("У покупателя %d eur%n",customers.get(numberCustomer - 1).getMoney());
        System.out.println("Введите сколько добавить денег: ");
        int addBalance = scanner.nextInt(); scanner.nextLine();
        customers.get(numberCustomer - 1).setMoney(customers.get(numberCustomer - 1).getMoney() + addBalance);
    }
    
    
    
}
