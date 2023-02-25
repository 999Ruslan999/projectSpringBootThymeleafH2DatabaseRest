package com.popov.popov.controller;


import com.popov.popov.entity.Customer;
import com.popov.popov.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
//        List<Customer> customers = customerService.getAll();
//        model.addAttribute("customers", customers);
return "customers";
    }



}
