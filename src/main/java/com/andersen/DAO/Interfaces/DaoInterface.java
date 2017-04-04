package com.andersen.DAO.Interfaces;


import java.util.List;

public interface DaoInterface<T> {

    public void add(T entity);

    public void delete(T entity);

    public List<T> findAll();

}
