package org.ace.webui.ds;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Customers {

    private List<Customer> customers;

    private Customer customer;

    public Customers(){}
}
