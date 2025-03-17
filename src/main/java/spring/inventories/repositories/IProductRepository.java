package spring.inventories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.inventories.models.Product;

public interface IProductRepository extends JpaRepository<Product, Integer> {

}
