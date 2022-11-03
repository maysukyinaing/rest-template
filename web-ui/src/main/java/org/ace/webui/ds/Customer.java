package org.ace.webui.ds;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class Customer {

    private Integer id;
    @NotBlank(message = "Code can't be empty")
    private String code;
    @NotBlank(message = "First Name can't be empty")
    private String firstName;
    @NotBlank(message = "Last Name can't be empty")
    private String lastName;

    public Customer(){}


}
