package com.example.microservice;

import javax.persistence.*;

@Entity
@Table(name="Order")
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    private Double unitPrice;

    private Integer quantity;

    protected Order(){}

    public Order(Customer customer, double unitPrice, int quantity) {
        this.customer= customer;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
