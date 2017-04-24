package com.andersen.Entities;


import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Set;


@Entity
public class OrderT {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToMany
    @JoinTable(name = "order_good", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "good_id", referencedColumnName = "id"))
    private Set<Good> goods;

    @Transient
    private String[] goodsString;

    @Transient
    public String[] getGoodsString() {
        return goodsString;
    }

    @Transient
    public void setGoodsString(String[] goodsString) {
        this.goodsString = goodsString;
    }

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
        return Objects.equal(id, orderT.id) &&
                Objects.equal(customer, orderT.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, customer);
    }

    @Override
    public String toString() {
        return "OrderT{" +
                "customer=" + customer +
                ", id=" + id +
                ", goods=" + goods +
                '}';
    }
}
