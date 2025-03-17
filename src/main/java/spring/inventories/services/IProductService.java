package spring.inventories.services;

import spring.inventories.models.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();
    public Product findById(Integer id);
    public Product save(Product product);
    public void deleteById(Integer id);
}
