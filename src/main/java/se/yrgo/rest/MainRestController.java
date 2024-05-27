package se.yrgo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import se.yrgo.data.*;
import se.yrgo.domain.*;

@RestController
public class MainRestController {

    private final CustomerRepo customerData;
    private final CustomerOrderRepo customerOrderData;
    private final OrderProductRepo orderProductData;
    private final ProductRepo productData;

    @Autowired
    public MainRestController(CustomerRepo customerData, CustomerOrderRepo customerOrderData,
            OrderProductRepo orderProductData, ProductRepo productData) {
        this.customerData = customerData;
        this.customerOrderData = customerOrderData;
        this.orderProductData = orderProductData;
        this.productData = productData;
    }

    @RequestMapping("/customers")
    public CustomerList allCustomers() {
        List<Customer> all = customerData.findAll();
        return new CustomerList(all);
    }

    @PostMapping("/addCustomers")
    public ResponseEntity<Object> createNewCustomers(@RequestBody List<Customer> customers) {
        if (customers.size() == 1) {
            // If only one customer is provided, save it and return
            Customer singleCustomer = customers.get(0);
            customerData.save(singleCustomer);
            return new ResponseEntity<>(singleCustomer, HttpStatus.CREATED);
        } else {
            // If multiple customers are provided, save them and return
            List<Customer> savedCustomers = customerData.saveAll(customers);
            return new ResponseEntity<>(savedCustomers, HttpStatus.CREATED);
        }
    }

    @RequestMapping("/customerOrders")
    public CustOrderList allCustOrders() {
        List<CustomerOrder> all = customerOrderData.findAll();
        return new CustOrderList(all);
    }

    @PostMapping("/addCustomerOrder")
    public ResponseEntity<CustomerOrder> createNewCustOrder(@RequestBody CustomerOrder customerOrder) {
        Long customerId = customerOrder.getCustomer().getCustomerId(); // Extract customerId from the incoming
                                                                       // CustomerOrder
        Customer customer = customerData.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        // Create a new Customer object using the retrieved customer
        CustomerOrder newOrder = new CustomerOrder(customer);

        // Save the new CustomerOrder
        customerOrderData.save(newOrder);

        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @RequestMapping("/orderProducts")
    public OrderProductList allOrderProducts() {
        List<OrderProduct> all = orderProductData.findAll();
        return new OrderProductList(all);
    }

    @PostMapping("/addOrderProduct")
    public ResponseEntity<OrderProduct> createNewOrderProduct(@RequestBody OrderProduct orderProduct) {
        customerOrderData.save(orderProduct.getCustomerOrder());

        String productName = orderProduct.getProduct().getProductName();
        Product product = productData.findByProductName(productName);

        OrderProduct newOrderProduct = new OrderProduct(orderProduct.getCustomerOrder(), product);

        orderProductData.save(orderProduct);
        return new ResponseEntity<>(newOrderProduct, HttpStatus.CREATED);
    }

    @RequestMapping("/products")
    public ProductList allProducts() {
        List<Product> all = productData.findAll();
        return new ProductList(all);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product) {
        productData.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
