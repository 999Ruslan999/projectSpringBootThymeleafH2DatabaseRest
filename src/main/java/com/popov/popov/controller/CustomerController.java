package com.popov.popov.controller;


import com.popov.popov.entity.Customer;
import com.popov.popov.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping(path={"/add", "/edit/{id}"})
    public String addCustomers(Model model, @PathVariable("id") Optional<Long> id) throws Exception {
//         logger.info("Adding customer");
         if (id.isPresent()) {
        Customer customer = customerService.getCustomerById(id.get());
            model.addAttribute("customer", customer);
        } else {
            model.addAttribute("customer", new Customer());
        }
        return "add-customers";
    }


    @PostMapping("/createOrUpdateCustomer")
    public String createOrUpdateCustomer(Customer customer) {
        logger.info("surname from customer: " + customer.getSurname());
        customerService.save(customer);
        return "redirect:/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomerById(@PathVariable("id") Long id) {
        logger.info("delete the customer, Id: " + id);
        customerService.delete(id);
        return "redirect:/all";
    }

}