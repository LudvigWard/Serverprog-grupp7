package se.yrgo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
    // OBS, ID ska vara kundens personnummer!
    @Id
    private Long customerId;

    private String customerName;
    private String email;
    private String mobileNum;

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

    public void setMobileNum(String phoneNumber) {
        this.mobileNum = phoneNumber;
    }
}
