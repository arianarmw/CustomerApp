package com.ariana.CustomerApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ariana.CustomerApp.model.Customer;
import com.ariana.CustomerApp.model.Gender;
import com.ariana.CustomerApp.model.Job;
import com.ariana.CustomerApp.service.CustomerService;
import com.ariana.CustomerApp.service.GenderService;
import com.ariana.CustomerApp.service.JobService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JobService jobService;

    @Autowired
    private GenderService genderService;

    @GetMapping("/form")
    public String formCustomer(Model model) {
        List<Job> jobs = jobService.getAllJobs();
        List<Gender> genders = genderService.getAllGenders();
        List<Customer> customers = customerService.getAllCustomers();

        model.addAttribute("jobs", jobs);
        model.addAttribute("genders", genders);
        model.addAttribute("customers", customers);
        model.addAttribute("customer", new Customer());
        return "CustomerForm";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        try {
            customerService.saveCustomer(customer);
            return "redirect:/customer/form";
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Umur tidak boleh negatif")) {
                model.addAttribute("ageError", errorMessage);
            } else if (errorMessage.contains("Penghasilan tidak boleh negatif")) {
                model.addAttribute("incomeError", errorMessage);
            }
            List<Job> jobs = jobService.getAllJobs();
            List<Gender> genders = genderService.getAllGenders();
            List<Customer> customers = customerService.getAllCustomers();

            model.addAttribute("jobs", jobs);
            model.addAttribute("genders", genders);
            model.addAttribute("customers", customers);
            return "CustomerForm";
        }
    }

    @PostMapping("/delete/{customerId}")
    public String deleteCustomer(@PathVariable("customerId") String customerId) {
        customerService.deleteCustomerById(customerId);
        return "redirect:/customer/form";
    }
}