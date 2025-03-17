package spring.inventories.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.inventories.models.Product;
import spring.inventories.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory") //http://localhost:8080/api/inventory
@CrossOrigin(value = "http://localhost:4200") //allow request from port 4200, this port is from Angular
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    //injection reference
    @Autowired
    private ProductService productService;

    @GetMapping("/products") //http://localhost:8080/api/inventarie/products
    public List<Product> findAll(){
        List<Product> products = this.productService.findAll();
        logger.info("Products: ");
        products.forEach(product -> logger.info(product.toString()));
        return products;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        logger.info("Product to add: " + product.toString());
        return this.productService.save(product);
    }
}
