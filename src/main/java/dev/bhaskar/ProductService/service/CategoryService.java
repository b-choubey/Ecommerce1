package dev.bhaskar.ProductService.service;

import dev.bhaskar.ProductService.dto.CategoryRequestDTO;
import dev.bhaskar.ProductService.dto.CategoryResponseDTO;
import dev.bhaskar.ProductService.exeception.CategoryNotFoundException;
import dev.bhaskar.ProductService.exeception.DuplicateCategoryNameException;
import dev.bhaskar.ProductService.exeception.ProductNotFoundException;
import dev.bhaskar.ProductService.model.Category;
import dev.bhaskar.ProductService.model.Product;
import dev.bhaskar.ProductService.repository.CategoryRepository;
import dev.bhaskar.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;
    public List<Category> getCategory() {
       return categoryRepository.findAll();
    }
    public Category addCategoryService(CategoryRequestDTO categoryRequestDTO){
        Category savedcategory=categoryRepository.findByName(categoryRequestDTO.getCategoryName())
                .orElseThrow(
                () -> new DuplicateCategoryNameException("Duplicate category name " + categoryRequestDTO.getCategoryName())

        );//lambda expression
        Category category=new Category();
        category.setName(categoryRequestDTO.getCategoryName());
        category.setDescription(categoryRequestDTO.getCategoryDescription());
        return categoryRepository.save(category);
    }
    public Category getCategoryById(int id){
        Category savedCategoryId= categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category id not found " + id)
        );
        return savedCategoryId;

    }
    public boolean deleteCategory(int id){
        Category category =getCategoryById(id);
        //here we are making object of category and checking if category exist with that id
        //once we confirmed we are taking all product from that category by using for loop
        //and delete each product one by one inside it using product service as our concept is
        //product can not exist without category while category can exist without product.
        for(Product product:category.getProducts()){
            productService.deleteProductById(product.getId());
        }
        categoryRepository.deleteById(id);
        return true;
    }
    public Category updateCategoryById(int id,Category category){
        Category categoryById=getCategoryById(id);
        category.setId(id);
        return categoryRepository.save(category);
    }
    //as product does not contain any category its one way mapped what we can do is let category service talk to product service anf
    //fetch the data from there
    //its a category thing because we are taking category input .
    public List<Product> getAllProductBycategory(int categoryId){
        Category category=getCategoryById(categoryId);
        List<Product>savedProducts=category.getProducts();
        return savedProducts;
    }

}
