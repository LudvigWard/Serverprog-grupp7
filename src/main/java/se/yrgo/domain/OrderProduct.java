package se.yrgo.domain;

import jakarta.persistence.*;

@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductId;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "productName", nullable = false)
    private Product product;

    public OrderProduct() {}

    public OrderProduct(CustomerOrder customerOrder, Product product) {
        this.customerOrder = customerOrder;
        this.product = product;
    }

    public Long getOrderProductId() {
        return orderProductId;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order ID: " + customerOrder.getOrderId() + "\n" +
                "Product name: " + product.getProductName() + "\n";
    }

}
