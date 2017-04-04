package com.andersen.Services;

import com.andersen.DAO.OrderDao;
import com.andersen.Entities.OrderT;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
public class OrderService {
    OrderDao orderDao;

    public OrderService() { }

    @Transactional
    public void add(OrderT orderT){
        orderDao.add(orderT);
    }

    @Transactional
    public void delete(OrderT orderT){
        orderDao.delete(orderT);
    }

    @Transactional
    public List<OrderT> findAll(){
        List<OrderT> orderTs = orderDao.findAll();
        return orderTs;
    }

}
