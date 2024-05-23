package se.yrgo.rest;

import java.util.List;
import se.yrgo.domain.CustomerOrder;

public class CustOrderList {
    private List<CustomerOrder> customerOrders;

    public CustOrderList() {
    }

    public CustOrderList(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

}
