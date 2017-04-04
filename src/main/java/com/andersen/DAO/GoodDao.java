package com.andersen.DAO;

import com.andersen.DAO.Interfaces.GoodDaoInterface;
import com.andersen.Entities.Good;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("goodDAO")
public class GoodDao implements GoodDaoInterface<Good> {

    @Autowired
    private SessionFactory sessionFactory;

    public GoodDao(){}


    @Override
    public void add(Good entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void delete(Good entity) {
sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<Good> findAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Good.class);
        return criteria.list();
    }
}
