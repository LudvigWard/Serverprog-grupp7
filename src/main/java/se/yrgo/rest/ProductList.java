package se.yrgo.rest;

import java.util.List;
import se.yrgo.domain.Product;

public class ProductList {
    private List<Product> products;

    public ProductList() {
    }

    public ProductList(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
