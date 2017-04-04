package com.andersen.Services;


import com.andersen.DAO.GoodDao;
import com.andersen.Entities.Good;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("goodService")
public class GoodService {
    GoodDao goodDao;

    public GoodService() {
    }


    @Transactional
    public void add(Good good){
        goodDao.add(good);
    }

    @Transactional
    public void delete(Good good){
        goodDao.delete(good);
    }

    @Transactional
    public List<Good> findAll(){
        List<Good> goods = goodDao.findAll();
        return goods;
    }

}
