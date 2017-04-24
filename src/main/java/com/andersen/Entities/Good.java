package com.andersen.Entities;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private int price;

    @ManyToMany(mappedBy = "goods")
    private Set<OrderT> orderTs;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public Set<OrderT> getOrderTs() {
        return orderTs;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOrderTs(Set<OrderT> orderTs) {
        this.orderTs = orderTs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Good)) return false;
        Good good = (Good) o;
        return Objects.equal(id, good.id) &&
                Objects.equal(price, good.price) &&
                Objects.equal(title, good.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title, price);
    }

    @Override
    public String toString() {
        return  "Title='" + title + '\'' +
                ", Price=" + price +
                '}';
    }
}
