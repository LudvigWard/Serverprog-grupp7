package se.yrgo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import se.yrgo.data.CustomerRepo;
import se.yrgo.domain.Customer;

@RestController
public class CustomerRestController {
    
    @Autowired
    private CustomerRepo data;

    @RequestMapping("/customers")
    public CustomerList allCustomers() {
        List<Customer> all = data.findAll();
        return new CustomerList(all);
    }
}
