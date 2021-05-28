package com.example.microservice;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.NamedQuery;
import java.util.List;

//@NamedQuery(name="pagingByRawNo",query = "select * from Customer limit 100 offset 250;")
public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    Customer findById(int id);

    List<Customer> findByName(String name);

    //List<Customer> pagingByRawNo();

//    @Query(value = "select c from Customer c limit 100 offset ?1")
//    List<Customer> pagingByRawNo(int rawNo);

}
