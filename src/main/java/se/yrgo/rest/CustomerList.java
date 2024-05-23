package se.yrgo.rest;

import java.util.List;
import se.yrgo.domain.Customer;

public class CustomerList {

    private List<Customer> customers;

    public CustomerList() {
    }

    public CustomerList(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
