package se.yrgo.domain;

import jakarta.persistence.*;

@Entity
public class Customer {
    // ID should be the customer's person/social security number!
    @Id
    private Long customerId;

    private String customerName;
    private String email;
    private String mobileNum;

    public Customer() {}

    public Customer(Long customerId, String customerName, String email, String mobileNum) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.mobileNum = mobileNum;
    }

    public void setCustomerId(Long id) {
        this.customerId = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + "\n" +
                "Customer name: " + customerName + "\n" +
                "Customer email: " + email + "\n" +
                "Customer mobile number: " + mobileNum + "\n";
    }
}
