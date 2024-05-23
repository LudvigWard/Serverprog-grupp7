package se.yrgo.domain;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    private String productName;
    
    private Long price; 

    public Product() {}

    public Product(String productName, Long price) {
        this.productName = productName;
        this.price = price;

    }

    public String getProductName() {
        return productName;
    }

    public Long getPrice() {
        return price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product name: " + productName + "\n" +
                "Product price: " + price + "\n";
    }
}
