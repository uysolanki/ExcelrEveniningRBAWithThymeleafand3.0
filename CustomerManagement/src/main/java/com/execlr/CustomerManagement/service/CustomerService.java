package com.execlr.CustomerManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.execlr.CustomerManagement.entity.Customer;
import com.execlr.CustomerManagement.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerrepo;

	public Customer addCustomer(Customer customer) {
		return customerrepo.save(customer);
		
	}

	public void deleteCustomer(int custid) {
		customerrepo.deleteById(custid);
	}

	public List<Customer> getAllCustomers() {
		return customerrepo.findAll();
	}

	public Customer getCustomer(int custid) {
		return customerrepo.findById(custid).get();
	}

	public void updateEmployee(int custid, Customer customer) {
		Customer custdb=customerrepo.findById(custid).get();
		custdb.setFirstName(customer.getFirstName());
		custdb.setLastName(customer.getLastName());
		custdb.setEmail(customer.getEmail());
		customerrepo.save(custdb);
		
	}
}
