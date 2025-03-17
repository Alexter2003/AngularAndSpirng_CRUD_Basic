package spring.inventories.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.inventories.models.Product;
import spring.inventories.repositories.IProductRepository;

import java.util.List;

@Service
public class ProductService implements IProductService{

    //injection reference
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        this.productRepository.deleteById(id);
    }
}
