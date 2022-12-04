package com.serenakd.springbootwebfluxdemo.dao;

import com.serenakd.springbootwebfluxdemo.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public List<Customer> getCustomers(){
        return IntStream.rangeClosed(1,50)
                .mapToObj(id -> new Customer(id, "Customer"+id))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream(){
        return Flux.range(1,50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count: " +i))
                .map(id -> new Customer(id, "customer"+id));
    }

}
