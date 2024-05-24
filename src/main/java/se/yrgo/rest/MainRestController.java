package se.yrgo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/customers")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
        customerData.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @RequestMapping("/customerOrders")
    public CustOrderList allCustOrders() {
        List<CustomerOrder> all = customerOrderData.findAll();
        return new CustOrderList(all);
    }

    @PostMapping("/customerOrders")
    public ResponseEntity<CustomerOrder> createNewCustOrder(@RequestBody CustomerOrder customerOrder) {
        customerOrderData.save(customerOrder);
        return new ResponseEntity<>(customerOrder, HttpStatus.CREATED);
    }

    @RequestMapping("/orderProducts")
    public OrderProductList allOrderProducts() {
        List<OrderProduct> all = orderProductData.findAll();
        return new OrderProductList(all);
    }

    @PostMapping("/orderProducts")
    public ResponseEntity<OrderProduct> createNewOrderProduct(@RequestBody OrderProduct orderProduct) {
        orderProductData.save(orderProduct);
        return new ResponseEntity<>(orderProduct, HttpStatus.CREATED);
    }

    @RequestMapping("/products")
    public ProductList allProducts() {
        List<Product> all = productData.findAll();
        return new ProductList(all);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product) {
        productData.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
