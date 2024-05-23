package se.yrgo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
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

    @RequestMapping(value="/customers", method=RequestMethod.POST)
    public ResponseEntity createNewCustomer(@RequestBody Customer customer){
        data.save(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }
}
