package org.ace.backend.ds;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Data
@AllArgsConstructor
public class Customers {

    private List<Customer> customers;

    private Customer customer;

    public Customers(){}

    public Customers(Spliterator<Customer> spliterator) {
        this.customers = StreamSupport.stream(spliterator, false).collect(Collectors.toList());
    }

    public Customers(Customer customer){
        this.customer = customer;
    }

}
