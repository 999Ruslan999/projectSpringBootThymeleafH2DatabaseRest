package com.popov.popov.service;

import com.popov.popov.entity.Customer;
import com.popov.popov.repository.CustomerRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  public Customer getCustomerById(Long id) throws Exception {
      Optional<Customer> customer = customerRepository.findById(id);
      if (customer.isPresent()) {
          return customer.get();
      } else {
          throw new Exception();
      }
  }

  public List<Customer> sortedCustomer() {
      return getAll().stream()
              .sorted(Comparator.comparing(Customer::getName))
                      .collect(Collectors.toList());
  }
}
