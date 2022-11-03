package org.ace.webui.validator;

import org.ace.webui.ds.Customer;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CustomerValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {

        return Customer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"code", "required.Code", "Code is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName", "required.FirstName", "First Name is required!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastName", "required.LastName", "Last Name is required!");
    }
}
