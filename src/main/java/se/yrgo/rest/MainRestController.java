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

    @PostMapping("/addCompleteOrder") //for this to be valid the customer data and the product data need to already exist in the database
    public ResponseEntity<CompleteOrder> createNewCompleteOrder(@RequestBody CompleteOrder completeOrder) {
        
        // Create a new customer object using the retrieved customerId from the completeOrder-object
        Long customerId = completeOrder.getCustomerId();
        Customer customer = customerData.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        // Create a new customerOrder using the customer object, and save this to the database
        CustomerOrder newOrder = new CustomerOrder(customer);
        customerOrderData.save(newOrder);

        // Find the products using the retrieved productName from the completeOrder-object. Then add these to the OrderProduct table along with the unique CustomerOrder
        for (String productName : completeOrder.getProductNames()) {
            Product product = productData.findByProductName(productName);
            OrderProduct orderProduct = new OrderProduct(newOrder, product);
            orderProductData.save(orderProduct);
        }   

        CompleteOrder newCompleteOrder = new CompleteOrder(completeOrder.getCustomerId(), completeOrder.getProductNames());

        return new ResponseEntity<>(newCompleteOrder, HttpStatus.CREATED);
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

        Long orderId = orderProduct.getCustomerOrder().getOrderId();
        CustomerOrder customerOrder = customerOrderData.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CustomerOrder not found"));

        String productName = orderProduct.getProduct().getProductName();
        Product product = productData.findByProductName(productName);

        OrderProduct newOrderProduct = new OrderProduct(customerOrder, product);

        orderProductData.save(orderProduct);

        return new ResponseEntity<>(newOrderProduct, HttpStatus.CREATED);
    }

    @RequestMapping("/products")
    public ProductList allProducts() {
        List<Product> all = productData.findAll();
        return new ProductList(all);
    }

    @PostMapping("/addProducts")
    public ResponseEntity<Object> createNewProducts(@RequestBody List<Product> products) {
        if (products.size() == 1) {
            // If only one product is provided, save it and return
            Product singleProduct = products.get(0);
            productData.save(singleProduct);
            return new ResponseEntity<>(singleProduct, HttpStatus.CREATED);
        } else {
            // If multiple products are provided, save them and return
            List<Product> savedProducts = productData.saveAll(products);
            return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
        }
    }
}
