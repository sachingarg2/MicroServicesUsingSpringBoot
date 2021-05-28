package com.example.microservice;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(max=20)
    @NotNull
    private String name;

    private String state;

    private String city;

    private Integer zip;

    @OneToMany(targetEntity = Order.class)
    List orders;

    protected Customer(){}
    public Customer(String name, String state, String city, int zip) {
        this.name = name;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.orders=new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public List getOrders() {
        return orders;
    }

    public void setOrders(List orders) {
        this.orders = orders;
    }

    public void placeOrder(Order order){
        orders.add(order);
    }

}
