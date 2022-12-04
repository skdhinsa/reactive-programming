package com.serenakd.springbootwebfluxdemo.service;

import com.serenakd.springbootwebfluxdemo.dao.CustomerDao;
import com.serenakd.springbootwebfluxdemo.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;

    public List<Customer> loadAllCustomers(){

        Long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomers();
        Long end = System.currentTimeMillis();
        System.out.println("Processing time: "+(end-start));

        return customers;
    }

    public Flux<Customer> loadAllCustomersStream(){
        Long start = System.currentTimeMillis();

        Flux<Customer> customers = dao.getCustomersStream();

        Long end = System.currentTimeMillis();
        System.out.println("Processing time: "+(end-start));

        return customers;
    }
}
