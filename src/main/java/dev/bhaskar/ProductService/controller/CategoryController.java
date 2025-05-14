package dev.bhaskar.ProductService.controller;

import dev.bhaskar.ProductService.exeception.CategoryNotFoundException;
import dev.bhaskar.ProductService.model.Category;
import dev.bhaskar.ProductService.repository.ProductRepository;
import dev.bhaskar.ProductService.service.CategoryService;
import dev.bhaskar.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<Category>>getCategory(){
        List<Category> getAllCategory= categoryService.getCategory();
        return ResponseEntity.status(HttpStatus.OK).body(getAllCategory);
    }
    @PostMapping("/category")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category addCategory=categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(addCategory);
    }
    @DeleteMapping("/category/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategory(id));
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getcategoryById(@PathVariable("id") int id){
        if(id<=0) throw new CategoryNotFoundException();
        Category getCategoryById=categoryService.getCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(getCategoryById);
    }
    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") int id,@RequestBody Category category){
        if(id<=0) throw new CategoryNotFoundException();
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategoryById(id,category));
    }

}
