package com.andersen;

import com.andersen.Entities.Customer;
import com.andersen.Entities.Good;
import com.andersen.Entities.OrderT;
import com.andersen.Repositories.CustomerRepository;
import com.andersen.Repositories.GoodRepository;
import com.andersen.Repositories.OrderRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Console;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Main {

    private static final String fNEW_LINE = System.getProperty("line.separator");

    public static void main(String[] args) {

        ClassPathXmlApplicationContext contex = new ClassPathXmlApplicationContext("applicationContext.xml");

        CustomerRepository customerRepository = contex.getBean(CustomerRepository.class);
        GoodRepository goodRepository = contex.getBean(GoodRepository.class);
        OrderRepository orderRepository = contex.getBean(OrderRepository.class);
        Console console = System.console();

        boolean back;

        do {

            back = false;

            console.printf("1. Administrator");
            console.printf(fNEW_LINE);
            console.printf("2. Customer");
            console.printf(fNEW_LINE);
            console.printf("3. Exit");
            console.printf(fNEW_LINE);

            int chose = 0;
            try {
                chose = Integer.parseInt(console.readLine("You are:"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            if (chose == 1) {
                do {
                    console.printf("1. Customer");
                    console.printf(fNEW_LINE);
                    console.printf("2. Good");
                    console.printf(fNEW_LINE);
                    console.printf("3. OrderT");
                    console.printf(fNEW_LINE);
                    console.printf("4. Back");
                    console.printf(fNEW_LINE);
                    int table = Integer.parseInt(console.readLine("Select the table to edit:"));
                    console.printf(fNEW_LINE);
                    switch (table) {
                        case 1:
                            editCustomers(console, customerRepository);
                            break;
                        case 2:
                            editGoods(console, goodRepository);
                            break;
                        case 3:
                            editOrders(console, orderRepository);
                            break;
                        default:
                            back = true;
                            break;
                    }
                } while (!back);
                back = false;

            } else if (chose == 2) {

                List<Customer> customers = customerRepository.findAll();
                int tmp = 1;
                for (Customer customer : customers) {
                    console.printf(tmp++ + ". " + customer.getFio());
                    console.printf(fNEW_LINE);
                }
                tmp = Integer.parseInt(console.readLine("Select your number:"));
                console.printf(fNEW_LINE);
                Customer customer = customers.get(tmp - 1);
                if (customer.checkPassword(console.readPassword("Enter password:"))) {
                    console.printf(fNEW_LINE);
                    List<Good> goods = goodRepository.findAll();
                    Set<Good> goodsSet = new HashSet<Good>();
                    tmp = 1;
                    for (Good good : goods) {
                        console.printf(tmp++ + ". " + good.getTitle() + " (" + good.getPrice() + ")");
                        console.printf(fNEW_LINE);
                    }
                    console.printf(fNEW_LINE);
                    console.printf("Select items for the order");
                    console.printf(fNEW_LINE);
                    do {
                        tmp = Integer.parseInt(console.readLine("Add goods(number):"));
                        console.printf(fNEW_LINE);
                        goodsSet.add(goods.get(tmp - 1));
                        if (console.readLine("Done (Y/N): ").toUpperCase().equals("Y")) {
                            console.printf(fNEW_LINE);
                            back = true;
                        }
                    } while (!back);
                    back = false;

                    OrderT orders = new OrderT(customer, goodsSet);
                    orderRepository.save(orders);
                } else {
                    console.printf("The password is incorrect");
                    console.printf(fNEW_LINE);
                    back = true;
                }
            } else {
                back = true;

            }
        } while (!back);


        console.printf("GOODBYE!!!");

        contex.close();
    }

    private static void editCustomers(Console console, CustomerRepository customerRepository) {
        int tmp = 1;
        boolean back;
        do {
            back = false;
            List<Customer> customers = customerRepository.findAll();
            for (Customer customer : customers) {
                console.printf(tmp++ + ". " + customer.getFio());
                console.printf(fNEW_LINE);
            }
            console.printf("1. Add:");
            console.printf(fNEW_LINE);
            console.printf("2. Select customer");
            console.printf(fNEW_LINE);
            console.printf("3. Back");
            console.printf(fNEW_LINE);
            tmp = Integer.parseInt(console.readLine("Select action:"));
            console.printf(fNEW_LINE);

            if (tmp == 1) {
                Customer customer = new Customer();
                customer.setFio(console.readLine("Enter full name: "));
                console.printf(fNEW_LINE);
                customer.setPassword(String.valueOf(console.readPassword("Enter password: ")));
                console.printf(fNEW_LINE);
                customerRepository.save(customer);
            } else if (tmp == 2) {
                tmp = Integer.parseInt(console.readLine("Select customer number:"));
                console.printf(fNEW_LINE);
                if (console.readLine("Delete? (Y/N):").toUpperCase().equals("Y")) {
                    customerRepository.delete(customers.get(tmp - 1));
                    console.printf(fNEW_LINE);
                } else if (console.readLine("Delete? (Y/N):").toUpperCase().equals("N")) {
                    back = true;
                    console.printf(fNEW_LINE);
                }
            } else if (tmp == 3) {
                back = true;
                console.printf(fNEW_LINE);
            }
        } while (!back);
    }

    private static void editGoods(Console console, GoodRepository goodRepository) {

        int tmp = 1;
        boolean back;
        do {
            back = false;
            List<Good> goodList = goodRepository.findAll();

            for (Good good : goodList) {
                console.printf(tmp++ + ". " + good.getTitle());
            }
            console.printf("1. Add:");
            console.printf(fNEW_LINE);
            console.printf("2. Select good:");
            console.printf(fNEW_LINE);
            console.printf("3. Back");
            console.printf(fNEW_LINE);
            tmp = Integer.parseInt(console.readLine("Select action:"));
            console.printf(fNEW_LINE);

            if (tmp == 1) {
                Good good = new Good();
                good.setTitle(console.readLine("Enter title:"));
                console.printf(fNEW_LINE);
                good.setPrice(Integer.parseInt(console.readLine("Enter price:")));
                console.printf(fNEW_LINE);
                goodRepository.save(good);
            } else if (tmp == 2) {
                tmp = Integer.parseInt(console.readLine("Select good number:"));
                console.printf(fNEW_LINE);
                if (console.readLine("Delete? (Y/N):").toUpperCase().equals("Y")) {
                    console.printf(fNEW_LINE);
                    goodRepository.delete(goodList.get(tmp - 1));
                } else if (console.readLine("Delete? (Y/N):").toUpperCase().equals("N")) {
                    back = true;
                    console.printf(fNEW_LINE);
                }
            } else if (tmp == 3) {
                back = true;
                console.printf(fNEW_LINE);
            }
        } while (!back);
    }

    private static void editOrders(Console console, OrderRepository orderRepository) {

        int tmp = 1;
        boolean back;
        do {
            back = false;

            List<OrderT> orderTList = orderRepository.findAll();

            for (OrderT orderT : orderTList) {
                console.printf(tmp++ + ". " + orderT.getCustomer().getFio());
                for (Good good : orderT.getGoods()) {
                    console.printf("- " + good.getTitle() + " (" + good.getPrice() + ")");
                }
            }
            console.printf("1. Select good:");
            console.printf(fNEW_LINE);
            console.printf("2. Back");
            console.printf(fNEW_LINE);
            tmp = Integer.parseInt(console.readLine("Select action:"));
            console.printf(fNEW_LINE);
            if (tmp == 1) {
                tmp = Integer.parseInt(console.readLine("Select order number:"));
                console.printf(fNEW_LINE);
                if (console.readLine("Delete? (Y/N):").toUpperCase().equals("Y")) {
                    orderRepository.delete(orderTList.get(tmp - 1));
                    console.printf(fNEW_LINE);
                } else if (console.readLine("Delete? (Y/N):").toUpperCase().equals("N")) {
                    back = true;
                    console.printf(fNEW_LINE);
                }
            } else if (tmp == 2) {
                back = true;
            }
        } while (!back);
    }
}
