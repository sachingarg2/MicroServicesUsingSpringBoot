package com.example.microservice;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.NamedQuery;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Integer> {

    @Query(value="select o.customer from Order o where o.orderId=id")
    Customer findCustomerByOrderId(int id);


}
