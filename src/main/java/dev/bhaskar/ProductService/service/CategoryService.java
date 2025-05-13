package dev.bhaskar.ProductService.service;

import dev.bhaskar.ProductService.model.Category;
import dev.bhaskar.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    ProductRepository productRepository;

    public static List<Category> getCategory() {
        List<Category> rsponse=productRepository.findAll();
    }
}
