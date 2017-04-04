package com.andersen.DAO;

import com.andersen.DAO.Interfaces.CustomerDaoInterface;
import com.andersen.Entities.Customer;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("customerDAO")
public class CustomerDao implements CustomerDaoInterface<Customer>{

    @Autowired
    private SessionFactory sessionFactory;

    public CustomerDao() {
    }

    @Override
    public void add(Customer entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void delete(Customer entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<Customer> findAll() {
        Criteria criteriaCustomer = sessionFactory.getCurrentSession().createCriteria(Customer.class);
        return criteriaCustomer.list();
    }
}
