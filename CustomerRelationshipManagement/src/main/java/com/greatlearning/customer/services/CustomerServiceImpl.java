/**
 * 
 */
package com.greatlearning.customer.services;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatlearning.customer.models.Customer;


/**
 * @author welcome
 *
 */
@Repository
public class CustomerServiceImpl implements CustomerService {
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	public CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

	}
	@Transactional
	public List<Customer> findAll() {
		Transaction tx=session.beginTransaction();
		
		List<Customer> customers=session.createQuery("from Customer").list();
		tx.commit();
		return customers;
	}

	@Transactional
	public Customer findById(int theId) {
		Customer customer=new Customer();
		Transaction tx=session.beginTransaction();
		customer=session.get(Customer.class, theId);
		tx.commit();
		return customer;
	}

	@Transactional
	public void save(Customer theCustomer) {
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(theCustomer);
		tx.commit();
	}

	@Transactional
	public void deleteById(int theId) {
		Transaction tx =session.beginTransaction();
		Customer customer=session.get(Customer.class,theId);
		session.delete(customer);
		tx.commit();
	}

}
