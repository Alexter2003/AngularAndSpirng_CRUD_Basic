package spring.inventories.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.inventories.exceptions.NotFoundException;
import spring.inventories.models.Product;
import spring.inventories.services.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory") //http://localhost:8080/api/inventory
@CrossOrigin(value = "http://localhost:4200") //allow request from port 4200, this port is from Angular
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    //injection reference
    @Autowired
    private ProductService productService;

    @GetMapping("/products") //http://localhost:8080/api/inventarie/products
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = this.productService.findAll();
        logger.info("Products: ");
        products.forEach(product -> logger.info(product.toString()));
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        logger.info("Product to add: " + product.toString());
        Product newProduct = this.productService.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Integer id) {
        logger.info("Product id to search: " + id);
        Product product = this.productService.findById(id);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            throw new NotFoundException("Product not found with id " + id);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        logger.info("Product id to update: " + id);
        Product updatedProduct = this.productService.findById(id);
        if (updatedProduct != null) {
            if (product.getDescription() != null) {
                updatedProduct.setDescription(product.getDescription());
            }

            if (product.getPrice() != null) {
                updatedProduct.setPrice(product.getPrice());
            }

            if (product.getStock() != null) {
                updatedProduct.setStock(product.getStock());
            }

            updatedProduct = this.productService.save(updatedProduct);
            return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
        } else {
            throw new NotFoundException("Product not found with id " + id);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Integer id) {
        logger.info("Product id to delete: " + id);
        if (this.productService.findById(id) == null) {
            throw new NotFoundException("Product not found with id " + id);
        }
        this.productService.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
