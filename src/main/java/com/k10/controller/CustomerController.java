package com.k10.controller;

import com.k10.dto.Customer;
import com.k10.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;


    @GetMapping("/")
    public String showHome() { return "";}

    @GetMapping("/get/{id}")
    public Customer getUser(@PathVariable Integer id) throws SQLException {
        return customerRepo.getCustomer(id);
    }

    @GetMapping("/get")
    public List<Customer> getCustomers() {
        return customerRepo.getCustomers();
    }

    @PostMapping("/edit")
    public void editCustomer(Customer customer) {
        customerRepo.saveCustomer(customer);
    }


}
