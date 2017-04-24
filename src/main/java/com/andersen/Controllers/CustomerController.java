package com.andersen.Controllers;

import com.andersen.Entities.Customer;
import com.andersen.Entities.Good;
import com.andersen.Entities.OrderT;
import com.andersen.Repositories.CustomerRepository;
import com.andersen.Repositories.GoodRepository;
import com.andersen.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class CustomerController{
    @Qualifier("customerRepository")
    @Autowired
    private CustomerRepository customerRepository;

    @Qualifier("goodRepository")
    @Autowired
    private GoodRepository goodRepository;

    @Qualifier("orderRepository")
    @Autowired
    private OrderRepository orderRepository;

    private Customer orderCustomer;

    @RequestMapping(value = "customer",method = RequestMethod.GET)
    public String customerPage(Model model){
        model.addAttribute("customer", new Customer());
        return "customer";
    }

    @RequestMapping(value = "customerSuccess", method = RequestMethod.GET)
    public String customerSuccessPage(@ModelAttribute("customer") Customer customer, Model model){

                model.addAttribute("order", new OrderT());
                model.addAttribute("listGoods",goodRepository.findAll());
                orderCustomer = customer;
                return "customerSuccess";

    }

    @RequestMapping(value = "customerSuccess", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("customer") Customer customer, Model model){

        List<Customer> customers = customerRepository.findAll();

        for (Customer customerCheck:customers){
            if (customerCheck.getPassword().equals(customer.getPassword())&&customerCheck.getFio().equals(customer.getFio())){
                model.addAttribute("order", new OrderT());
                model.addAttribute("listGoods",goodRepository.findAll());
                orderCustomer = customer;
                return "customerSuccess";
            }
        }
        return "redirect:/customer";
    }

    @RequestMapping(value = "/customerSuccess/newOrder", method = RequestMethod.POST)
    public String addGood(@ModelAttribute("order") OrderT orderT){

        Set<Good> goodSet = new HashSet<>();
        List<Good> goods = goodRepository.findAll();

        outer:
        for (String goodID:orderT.getGoodsString()){
            for (Good good:goods){
                if (Integer.parseInt(goodID)==good.getId()){
                    goodSet.add(good);
                    continue outer;
                }
            }
        }

        orderT.setCustomer(orderCustomer);
        orderT.setGoods(goodSet);
        orderRepository.save(orderT);

        return "redirect:/customerSuccess";
    }
}
