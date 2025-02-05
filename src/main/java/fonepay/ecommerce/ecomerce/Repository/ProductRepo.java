package fonepay.ecommerce.ecomerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fonepay.ecommerce.ecomerce.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
