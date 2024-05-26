package se.yrgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import se.yrgo.data.CustomerOrderRepo;
import se.yrgo.data.CustomerRepo;
import se.yrgo.data.OrderProductRepo;
import se.yrgo.data.ProductRepo;
import se.yrgo.domain.Customer;
import se.yrgo.domain.CustomerOrder;
import se.yrgo.domain.OrderProduct;
import se.yrgo.domain.Product;

import java.util.List;

@Controller
@RequestMapping("/website/store")
public class MainController {
    @Autowired
    private CustomerRepo customerData;

    @Autowired
    private CustomerOrderRepo customerOrderData;

    @Autowired
    private OrderProductRepo orderProductData;

    @Autowired
    private ProductRepo productData;

    @RequestMapping(value="/customers.html", method= RequestMethod.GET)
    public ModelAndView customers(){
        List<Customer> allCustomers = customerData.findAll();
        return new ModelAndView("allCustomers" , "customers", allCustomers);
    }

    @RequestMapping(value="/customerOrders.html", method= RequestMethod.GET)
    public ModelAndView customerOrders(){
        List<CustomerOrder> allCustomerOrders = customerOrderData.findAll();
        return new ModelAndView("allCustomerOrders" , "customerOrders", allCustomerOrders);
    }

    @RequestMapping(value="/orderProducts.html", method= RequestMethod.GET)
    public ModelAndView orderProducts(){
        List<OrderProduct> allOrderProducts = orderProductData.findAll();
        return new ModelAndView("allOrderProducts" , "orderProducts", allOrderProducts);
    }

    @RequestMapping(value="/products.html", method= RequestMethod.GET)
    public ModelAndView products(){
        List<Product> allProducts = productData.findAll();
        return new ModelAndView("allProducts" , "products", allProducts);
    }
}
