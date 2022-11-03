package org.ace.backend.controller;

import org.ace.backend.dao.CustomerDao;
import org.ace.backend.ds.Customer;
import org.ace.backend.ds.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerDao customerDao;

    @GetMapping("/customers")
    public Customers listCustomers() {
        return new Customers(customerDao.findAll().spliterator());
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerDao.save(customer);
    }

    @GetMapping("/customers/{id}")
    public Customers editCustomer(@PathVariable int id) {
            return new Customers(customerDao.findById(id).get());
    }

    @PostMapping("/customers/update")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerDao.save(customer);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity deleteCustomer(@PathVariable int id) {
        if(customerDao.existsById(id)){
            customerDao.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

  }
