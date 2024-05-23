package se.yrgo.rest;

import se.yrgo.domain.OrderProduct;
import java.util.*;

public class OrderProductList {
    private List<OrderProduct> orderProducts;

    public OrderProductList() {
    }

    public OrderProductList(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

}
