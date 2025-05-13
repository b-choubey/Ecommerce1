package dev.bhaskar.ProductService.controller;

import dev.bhaskar.ProductService.model.Category;
import dev.bhaskar.ProductService.repository.ProductRepository;
import dev.bhaskar.ProductService.service.CategoryService;
import dev.bhaskar.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<Category>>getCategory(){
        List<Category> getAllCategory= CategoryService.getCategory();
        return ResponseEntity.status(HttpStatus.OK).body(getAllCategory);
    }
}
