package org.ace.backend.dao;

import org.ace.backend.ds.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends CrudRepository<Customer,Integer> {
}
