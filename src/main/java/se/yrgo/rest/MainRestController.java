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
        Customer customer = customerData.findById(customerOrder.getCustomer().getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        customerOrder.setCustomer(customer);
        customerOrderData.save(customerOrder);
        return new ResponseEntity<>(customerOrder, HttpStatus.CREATED);
    }

    // Endpoint for adding multiple customer orders
    @PostMapping("/addMultipleCustomerOrders")
    public ResponseEntity<List<CustomerOrder>> createNewCustOrders(@RequestBody List<CustomerOrder> customerOrders) {
        List<CustomerOrder> savedOrders = new ArrayList<>();
        for (CustomerOrder order : customerOrders) {
            Customer customer = customerData.findById(order.getCustomer().getCustomerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
            order.setCustomer(customer);
            savedOrders.add(order);
        }
        savedOrders = customerOrderData.saveAll(savedOrders);
        return new ResponseEntity<>(savedOrders, HttpStatus.CREATED);
    }

    @RequestMapping("/orderProducts")
    public OrderProductList allOrderProducts() {
        List<OrderProduct> all = orderProductData.findAll();
        return new OrderProductList(all);
    }

    @PostMapping("/addOrderProduct")
    public ResponseEntity<OrderProduct> createNewOrderProduct(@RequestBody OrderProduct orderProduct) {
        customerOrderData.save(orderProduct.getCustomerOrder());
        productData.save(orderProduct.getProduct());
        orderProductData.save(orderProduct);
        return new ResponseEntity<>(orderProduct, HttpStatus.CREATED);
    }

    // Endpoint for adding multiple order products in a batch
    @PostMapping("/addMultipleOrderProducts")
    public ResponseEntity<List<OrderProduct>> createNewOrderProductsBatch(
            @RequestBody List<OrderProduct> orderProducts) {
        List<OrderProduct> savedProducts = new ArrayList<>();
        for (OrderProduct product : orderProducts) {
            customerOrderData.save(product.getCustomerOrder());
            productData.save(product.getProduct());
            savedProducts.add(product);
        }
        savedProducts = orderProductData.saveAll(savedProducts);
        return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
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

    // Endpoint for adding multiple products
    @PostMapping("/addMultipleProducts")
    public ResponseEntity<List<Product>> createNewProducts(@RequestBody List<Product> products) {
        List<Product> savedProducts = productData.saveAll(products);
        return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
    }

}
