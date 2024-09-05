package com.ariana.CustomerApp.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ariana.CustomerApp.model.Customer;
import com.ariana.CustomerApp.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAllByOrderByCreatedAtAsc();
    }

    public Customer getCustomerById(String customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    public void saveCustomer(Customer customer) {
        if (customer.getAge() < 0) {
            throw new IllegalArgumentException("Umur tidak boleh negatif.");
        }
        if (customer.getIncome().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Penghasilan tidak boleh negatif.");
        }

        boolean exists = customerRepository.existsById(customer.getCustomerId());

        if (exists) {
            customerRepository.save(customer);
        } else {
            customerRepository.save(customer);
        }
    }

    public void deleteCustomerById(String customerId) {
        customerRepository.deleteById(customerId);
    }
}