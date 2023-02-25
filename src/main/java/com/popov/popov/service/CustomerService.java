package com.popov.popov.service;

import com.popov.popov.entity.Customer;
import com.popov.popov.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAll() {
       return (List<Customer>) customerRepository.findAll();
    }

    public void delete(Long customerId) {
        customerRepository.existsById(customerId);
    }

    public Customer update(Customer newCustomer) {
       Optional<Customer> oldCustomer = customerRepository.findById(newCustomer.getId());
       Customer c = null;
       if(oldCustomer.isPresent()) {
           c =oldCustomer.get();
           c.setName(newCustomer.getName());
           c.setSurname(newCustomer.getSurname());
           c.setEmail(newCustomer.getEmail());
       }
       return customerRepository.save(c);
    }
}
