package com.example.microservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.tomcat.websocket.Util.isPrimitive;
import static org.springframework.data.util.NullableWrapperConverters.unwrap;

@RestController
public class Controller {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){

        List<Customer> customers=new ArrayList<>();
        for(Customer customer:customerRepository.findAll()){
            customers.add(customer);
        }
        return customers;
    }


//    @GetMapping("/customers/{rawNo}")
//    public List<Customer> getCustomersByPaging(@PathVariable int rawNo) throws ClassNotFoundException, SQLException {
//
//        List<Customer> customers=new ArrayList<>();
//
//        Class.forName("org.postgresql.Driver");
//        Connection c = DriverManager
//                .getConnection("jdbc:postgresql://localhost:5432/Assignment?useSSL=false",
//                        "postgres", "Sachin@123");
//
//        Statement stmt = c.createStatement();
//        String sql = "select * from Customer limit 100 offset "+rawNo;
//        ResultSet rs= stmt.executeQuery(sql);
//
//        while (rs.next()) {
//            Customer customer = new Customer();
//            customer= rs.unwrap(Customer.class);
//
//            customers.add(customer);
//        }
//
//        stmt.close();
//        c.close();
//
//        return customers;
//    }

//    @GetMapping("/customers/{pageNo}")
//    public List<Customer> getCustomerByPaging(@PathVariable int pageNo){
//        Pageable paging = PageRequest.of(pageNo,5);
//        Page<Customer> pagedResult = customerRepository.findAll();
//        if(pagedResult.hasContent()){
//            return pagedResult.getContent();
//        }else{
//            return new ArrayList<Customer>();
//        }
//    }

    @PostMapping("/saveCustomer")
    void saveCustomer(@Valid @RequestBody Customer customer){
        customerRepository.save(customer);
    }


    @DeleteMapping("/deleteAllCustomers")
    void deleteAllCustomers(){
        orderRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        List<Order> orders=new ArrayList<>();
        for(Order order:orderRepository.findAll()){
            orders.add(order);
        }
        return orders;
    }


    @PostMapping("/placeOrder")
    void placeOrder(@Valid @RequestBody Order order){
        orderRepository.save(order);
        Customer customer= order.getCustomer();
        customer.placeOrder(order);
    }

    @DeleteMapping("/deleteAllOrders")
    void deleteAllOrders(){
        orderRepository.deleteAll();
        for(Customer customer:customerRepository.findAll()){
            customer.orders.clear();
        }
    }

    @PostMapping("/callProcedure")
    public void callProcedure(@Valid @RequestBody Order order) throws SQLException, ClassNotFoundException {
        order.getCustomer().placeOrder(order);
        StoredProcedure.updateOrders(order.getCustomer().getId(),order.getUnitPrice(),order.getQuantity());
    }



}
