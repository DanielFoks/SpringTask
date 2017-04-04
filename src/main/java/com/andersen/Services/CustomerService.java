package com.andersen.Services;

import com.andersen.DAO.CustomerDao;
import com.andersen.Entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("customerService")
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    public CustomerService(){
    }

    @Transactional
    public void add(Customer customer){
        customerDao.add(customer);
    }

    @Transactional
    public void delete(Customer customer){
        customerDao.delete(customer);
    }

    @Transactional
    public List<Customer> findAll(){
        List<Customer> customers = customerDao.findAll();
        return customers;
    }

}
