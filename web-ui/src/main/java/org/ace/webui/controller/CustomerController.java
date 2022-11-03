package org.ace.webui.controller;

import org.ace.webui.ds.Customer;
import org.ace.webui.ds.Customers;
import org.ace.webui.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class CustomerController {

    @Value("${app.backend.url}")
    private String backendUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/customers")
    public ModelAndView index() {
        ResponseEntity<Customers> responseEntity = restTemplate.getForEntity(backendUrl +"api/customers",Customers.class);
        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            return new ModelAndView("customers", "customers", responseEntity.getBody().getCustomers());
        }
        else {
            throw new IllegalStateException(String.format("Unable to list customers, received status %s", responseEntity.getStatusCode()));
        }
    }

    @GetMapping("/customers/create")
    public ModelAndView create() {
        return new ModelAndView("customer-create", "customer", new Customer());
    }

    @PostMapping("/customers/create")
    public String create(@Valid @ModelAttribute Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customer-create";
        }
        else {
            ResponseEntity<Customer> responseEntity = restTemplate
                    .postForEntity(backendUrl + "api/customers", customer, Customer.class);
            if(responseEntity.getStatusCode() != HttpStatus.OK) {
                throw new IllegalStateException(String.format("Unable to create customer, received status %s", responseEntity.getStatusCode()));
            }
            return  "redirect:/customers";
        }
    }

    @GetMapping("/customers/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ResponseEntity<Customers> responseEntity = restTemplate.getForEntity(backendUrl + "api/customers/"+id ,Customers.class);
        return new ModelAndView("customer-edit", "customer",  responseEntity.getBody().getCustomer());
    }

    @PostMapping("/customers/update/{id}")
    public String update(@PathVariable int id, @Valid @ModelAttribute Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            customer.setId(id);
            return "customer-edit";
        }
        else {
            ResponseEntity<Customer> responseEntity = restTemplate
                    .postForEntity(backendUrl + "api/customers/update", customer, Customer.class);
            if(responseEntity.getStatusCode() != HttpStatus.OK) {
                throw new IllegalStateException(String.format("Unable to update customer, received status %s", responseEntity.getStatusCode()));
            }
            return  "redirect:/customers";
        }
    }


    @GetMapping("/customers/delete/{id}")
    public String delete(@PathVariable int id) {
        restTemplate.delete(backendUrl + "/api/customers/{id}", id);
        return "redirect:/customers";
    }

    //Custom Validation
    //@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new CustomerValidator());
    }


}
