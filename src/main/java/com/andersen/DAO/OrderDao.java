package com.andersen.DAO;

import com.andersen.DAO.Interfaces.OrderDaoInterface;
import com.andersen.Entities.OrderT;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDAO")
public class OrderDao implements OrderDaoInterface<OrderT> {

    @Autowired
    private SessionFactory sessionFactory;


    public OrderDao(){}

    @Override
    public void add(OrderT entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void delete(OrderT entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<OrderT> findAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrderT.class);
        return criteria.list();
    }
}
