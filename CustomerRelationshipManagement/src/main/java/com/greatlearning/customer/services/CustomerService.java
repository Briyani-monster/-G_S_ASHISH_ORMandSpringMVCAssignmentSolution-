package com.greatlearning.customer.services;

import java.util.List;

import com.greatlearning.customer.models.Customer;

public interface CustomerService {
	public List<Customer> findAll();

	public Customer findById(int theId);

	public void save(Customer theCustomer);

	public void deleteById(int theId);

}
