package se.yrgo.data;
import java.util.List;


public class CompleteOrder {

    private Long customerId;
    private List<String> productNames;

    public CompleteOrder() {}

    public CompleteOrder(Long customerId, List<String> productNames) {
        this.customerId = customerId;
        this.productNames = productNames;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public List<String> getProductNames() {
        return productNames;
    }
}
