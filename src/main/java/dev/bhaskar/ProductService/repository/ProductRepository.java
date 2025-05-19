package dev.bhaskar.ProductService.repository;

import dev.bhaskar.ProductService.dto.ProductProjection;
import dev.bhaskar.ProductService.model.Category;
import dev.bhaskar.ProductService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//this annotation is for to create bean for dependency injection and better readability
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    //custom method created by us
    //spring has created some templates like findAll findFirst
    //if I put my attribute and extra suffix ex findAllByDescriptionIgnoreCase
    //Jpa define for us and then create query according.
    List<Product>findAllByDescription(String description);
    ProductProjection findFirstByName(String name);
    //for this check a link "Jpa query methods by spring"
    //how spring take care of this type of things
    //we can not pass findAllByCategoryId
    //because product does not have any category id as it's a foreign key so we are going through
    //category object
    //List<Product> findAllByCategory(Category category);
}
//Extending JpaRepository adds all fundamental crud operation methods in
// ProductRepository interface we don't need to implement those methods spring data jpa will do it for Us,
//we will just use them directly
//we can also add out own abstract method that will also jpa will implement