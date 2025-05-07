package dev.bhaskar.ProductService.controller;

import dev.bhaskar.ProductService.client.FakeStoreClient;
import dev.bhaskar.ProductService.dto.FakeStoreProductDTO;
import dev.bhaskar.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public FakeStoreProductDTO[] getAllProducts(){
        return productService.getALLProductsFromFakestore();
    }
   /*  @GetMapping("/product/{id}")
   public FakeStoreProductDTO getProduct(@PathVariable("id") int id){
        return productService.getALLProductsFromFakestore()[];
    }*/
}
