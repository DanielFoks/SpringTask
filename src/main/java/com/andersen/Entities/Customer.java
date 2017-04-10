package com.andersen.Entities;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String fio;

    private String password;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<OrderT> orderTs;

    public Customer(){}

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<OrderT> getOrderTs() {
        return orderTs;
    }

    public void setOrderTs(List<OrderT> orderTs) {
        this.orderTs = orderTs;
    }

    public boolean checkPassword(char[] password){
        if (String.valueOf(password).equals(this.password)){
            return true;
        }
        else return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equal(id, customer.id) &&
                Objects.equal(fio, customer.fio) &&
                Objects.equal(password, customer.password) &&
                Objects.equal(orderTs, customer.orderTs);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, fio, password, orderTs);
    }
}
