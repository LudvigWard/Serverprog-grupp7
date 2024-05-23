package se.yrgo.domain;

import jakarta.persistence.*;

@Entity
public class CustomerOrder {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    public CustomerOrder() {}

    public CustomerOrder(Customer customer) {
        this.customer = customer;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + "\n" + 
                "Customer ID: " + customer.getCustomerId() + "\n" + 
                "Customer name: " + customer.getCustomerName() + "\n";
    }

}
