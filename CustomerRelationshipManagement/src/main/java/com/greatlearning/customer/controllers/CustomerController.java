package com.greatlearning.customer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.greatlearning.customer.models.Customer;
import com.greatlearning.customer.services.CustomerService;




@Controller
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private CustomerService  customerService;
	
	@RequestMapping("/list")
	public String showList(Model theModel) {
		//get Customer from db
		List<Customer> customers=customerService.findAll();
		System.out.println(customers);
		theModel.addAttribute("Customers",customers);
		return "list-customers";
	}
	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("customerId") int id) {
		customerService.deleteById(id);
		return "redirect:/customer/list";
	}
	@PostMapping("/save")
	public String addStudent(@RequestParam("id") int id,@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("email") String email) {
		System.out.println(id);
		Customer theCustomer;
		if (id != 0) {
			theCustomer = customerService.findById(id);
			theCustomer.setFirstName(firstName);
			theCustomer.setLastName(lastName);
			theCustomer.setEmail(email);
		} else
			theCustomer = new Customer(id,firstName,lastName,email);
		// save the Book
		customerService.save(theCustomer);

		// use a redirect to prevent duplicate submissions
		return "redirect:/customer/list";	
	}
	@RequestMapping("/showForm")
	public String showFormAdd(Model theModel) {
		Customer customer=new Customer();
		theModel.addAttribute("Customer", customer);
		return "customer-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		System.out.println(theId);
		// get the Book from the service
		Customer theCustomer = customerService.findById(theId);
		System.out.println("the obj"+ theCustomer);
		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Customer", theCustomer);

		// send over to our form
		return "customer-form";
	}
}
