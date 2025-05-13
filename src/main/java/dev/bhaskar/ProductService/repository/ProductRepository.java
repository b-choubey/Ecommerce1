package dev.bhaskar.ProductService.repository;

import dev.bhaskar.ProductService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//this annotation is for to create bean for dependency injection and better readability
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
//Extending JpaRepository adds all fundamental crud operation methods in
// ProductRepository interface we don't need to implement those methods spring data jpa will do it for us
//we will just use them directly
//we can also add out own abstract method that will also jpa will implement