package dev.bhaskar.ProductService.controller;

import dev.bhaskar.ProductService.dto.CategoryRequestDTO;
import dev.bhaskar.ProductService.dto.CategoryResponseDTO;
import dev.bhaskar.ProductService.exeception.CategoryNotFoundException;
import dev.bhaskar.ProductService.model.Category;
import dev.bhaskar.ProductService.repository.ProductRepository;
import dev.bhaskar.ProductService.service.CategoryService;
import dev.bhaskar.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//during versioning can check notes for that instead of putting /vx/Api everyplace we can put this
//@RequestMapping("/v1/") by putting this every api will add v1

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<CategoryResponseDTO>>getCategory(){
        List<Category> getAllCategory= categoryService.getCategory();
        List<CategoryResponseDTO> categoryResponseDTOS=new ArrayList<>();
        for(Category category : getAllCategory){
            CategoryResponseDTO responseDTO=new CategoryResponseDTO(
                    category.getName(),category.getDescription()
            );
            categoryResponseDTOS.add(responseDTO);
        }
        return ResponseEntity.ok(categoryResponseDTOS);
    }
    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        Category addCategory=categoryService.addCategoryService(categoryRequestDTO);
        CategoryResponseDTO categoryResponseDTO=new CategoryResponseDTO(
                addCategory.getName(),addCategory.getDescription());
        return ResponseEntity.ok(categoryResponseDTO);
    }
    @DeleteMapping("/category/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategory(id));
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponseDTO> getcategoryById(@PathVariable("id") int id){
        if(id<=0) throw new CategoryNotFoundException();
        Category savedCategoryById=categoryService.getCategoryById(id);
        return ResponseEntity.ok(new CategoryResponseDTO(savedCategoryById.getName(),savedCategoryById.getDescription()));
    }
    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") int id,@RequestBody Category category){
        if(id<=0) throw new CategoryNotFoundException();
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategoryById(id,category));
    }

}
