package dev.bhaskar.ProductService.controller;

import dev.bhaskar.ProductService.client.FakeStoreClient;
import dev.bhaskar.ProductService.dto.*;
import dev.bhaskar.ProductService.model.Product;
import dev.bhaskar.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    //**Now we will create apis for our own database names demo
    //now here we will add get product from category
//    @GetMapping("/product/category/{id}")
//    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByCategory(@PathVariable("id") int categoryId) {
//        List<Product> saveProducts=productService.getAllProductByCategoryId(categoryId);
//        List<ProductResponseDTO> productResponseDTOS=new ArrayList<>();
//        for(Product product:saveProducts){
//            ProductResponseDTO productResponseDTO=new ProductResponseDTO(
//                    product.getPrice(),
//                    product.getName(),
//                    product.getDescription(),
//                    product.getRating()
//            );
//
//        }
//        return ResponseEntity.ok(productResponseDTOS);
//    }
    @PostMapping("/product")
    public ResponseEntity<Product>createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        //if it's incoming it is always a request
        //if it is outgoing then it is a response
        Product savedProduct = productService.saveProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(savedProduct);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product>getProductById(@PathVariable("id") int id){
        // we use @PathVariable to get the id not @RequestParam
        Product getProductById=productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(getProductById);
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable("id") int id){
        boolean deleteProduct=productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleteProduct);
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>>getProduct(){
        List<Product> getAllProduct= productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(getAllProduct);
    }
    @GetMapping("/product/description")
    public ResponseEntity<List<Product>>getProductByDescription(@PathVariable("description") String description){
        List<Product> getByDescription=productService.getProductByDesc(description);
        return ResponseEntity.status(HttpStatus.OK).body(getByDescription);
    }
    @GetMapping("/product/v1/{name}")
    public ResponseEntity<ProductProjection>getProductByProjection(@PathVariable("name") String name){
        ProductProjection getByProjection=productService.getProductByProjection(name);
        return ResponseEntity.status(HttpStatus.OK).body(getByProjection);
    }
    //**All below apis for FakeStore
    @GetMapping("/products/fake")
    public FakeStoreProductDTO[] getAllProductsFakeStore() {
        return productService.getALLProductsFromFakestore();
    }

      @GetMapping("/products/fake/{id}")
    public ResponseEntity<FakeStoreProductDTO> getProduct(@PathVariable("id") int id){
       if(id<=0){
           throw new IllegalArgumentException("product does not exist");
           //return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
       }
         FakeStoreProductDTO fakeStoreProductDTO= productService.getProductById(id);
       return new ResponseEntity<>(fakeStoreProductDTO, HttpStatus.OK);
     }
    //as a best response we are sending back the product that we created
    //but as post mapping we should not respond with anything so we are returning FakeStoreProductDTO
    @PostMapping("/products/fake")
    public FakeStoreProductDTO createProduct(@RequestBody FakeStoreProductDTO fakeStoreProductDTO) {
        return productService.createProduct(fakeStoreProductDTO);
    }
    @PutMapping("/products/fake/{id}")
    public FakeStoreProductDTO replaceProduct(@RequestBody FakeStoreProductDTO fakeStoreProductDTO, @PathVariable("id") int id){
        return productService.replaceProduct(fakeStoreProductDTO,id);
    }
    @DeleteMapping("/products/fake/{id}")
    public Boolean deleteProduct(@PathVariable("id") int id){
        if(getProduct(id) == null){return true;}
        return productService.deleteProductById(id);
    }
    //@RequestBody what we are sending as request to let spring know to convert in fakeStoreDTO
    //without this annotation whatever we are sending will never get mapped
}