package com.andersen.Entities;


import javax.persistence.*;
import java.util.Set;


@Entity
public class OrderT {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToMany
    @JoinTable(name = "order_good", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "good_id", referencedColumnName = "id"))
    private Set<Good> goods;

    public OrderT(){}

    public OrderT(Customer customer, Set<Good> goods) {
        this.customer = customer;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<Good> getGoods() {
        return goods;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setGoods(Set<Good> goods) {
        this.goods = goods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderT)) return false;
        OrderT orderT = (OrderT) o;
        return com.google.common.base.Objects.equal(id, orderT.id) &&
                com.google.common.base.Objects.equal(customer, orderT.customer) &&
                com.google.common.base.Objects.equal(goods, orderT.goods);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(id, customer, goods);
    }
}
