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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AdministratorController {

    @Autowired
    @Qualifier("customerRepository")
    CustomerRepository customerRepository;

    @Qualifier("goodRepository")
    @Autowired
    GoodRepository goodRepository;

    @Qualifier("orderRepository")
    @Autowired
    OrderRepository orderRepository;


    @RequestMapping(value = "administrator",method = RequestMethod.GET)
    public String adminPage(){
        return "administrator";
    }

    @RequestMapping(value = "customerAdmin",method = RequestMethod.GET)
    public String listCustomers(Model model){
        model.addAttribute("customer", new Customer());
        model.addAttribute("listCustomers", this.customerRepository.findAll());
        return "customerAdmin";
    }

    @RequestMapping(value = "goodAdmin",method = RequestMethod.GET)
    public String listGoods(Model model){
        model.addAttribute("good", new Good());
        model.addAttribute("listGoods", this.goodRepository.findAll());
        return "goodAdmin";
    }

    @RequestMapping(value = "orderAdmin",method = RequestMethod.GET)
    public String listOrders(Model model){
        model.addAttribute("order", new OrderT());
        model.addAttribute("listOrders", this.orderRepository.findAll());
        return "orderAdmin";
    }

    @RequestMapping(value = "/customerAdmin/add", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("customer") Customer customer){
        if(customer.getId() == 0){
            this.customerRepository.save(customer);
        }
        return "redirect:/customerAdmin";
    }

    @RequestMapping(value = "/goodAdmin/add", method = RequestMethod.POST)
    public String addGood(@ModelAttribute("good") Good good){
        if(good.getId() == 0){
            this.goodRepository.save(good);
        }
        return "redirect:/goodAdmin";
    }

    @RequestMapping("/customerAdmin/remove/{id}")
    public String removeCustomer(@PathVariable("id") int id){
        this.customerRepository.delete(id);
        return "redirect:/customerAdmin";
    }

    @RequestMapping("/goodAdmin/remove/{id}")
    public String removeGood(@PathVariable("id") int id){
        this.goodRepository.delete(id);
        return "redirect:/customerAdmin";
    }

    @RequestMapping("/orderAdmin/remove/{id}")
    public String removeOrder(@PathVariable("id") int id){
        this.orderRepository.delete(id);
        return "redirect:/customerAdmin";
    }

}
