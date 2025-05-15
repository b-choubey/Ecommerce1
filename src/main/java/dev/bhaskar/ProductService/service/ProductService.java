package dev.bhaskar.ProductService.service;

import dev.bhaskar.ProductService.client.FakeStoreClient;
import dev.bhaskar.ProductService.dto.FakeStoreProductDTO;
import dev.bhaskar.ProductService.dto.ProductProjection;
import dev.bhaskar.ProductService.exeception.ProductNotFoundException;
import dev.bhaskar.ProductService.model.Product;
import dev.bhaskar.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    //it will be dependent on fakeStoreClient
    @Autowired
    private FakeStoreClient fakeStoreClient;
    //it will add di for ProductRepository
    @Autowired
    private ProductRepository productRepository;
    //now we will create 4 methods
    public Product saveProduct(Product product){
        //to save the product we will call save method of ProductRepository which is
        //implementing JpaRepository which contain save class
        Product savedProduct=productRepository.save(product);
        return savedProduct;

    }
    public Product updateProduct(Product newproduct,int productId){
        Product savedProduct=getProduct(productId);
        newproduct.setId(productId);
        Product updatedProduct= productRepository.save(newproduct);
        return updatedProduct;
    }
    public boolean deleteProduct(int productId){
        productRepository.deleteById(productId);
        return true;
    }

    public Product getProduct(int productId){
        //only writing findById(productId) it will show error if you see in the class
        //we will be able to see that it's returning optional which means it can have value
        //or can be null so in that case we add .get()
        //.get() will give us option
        //or better way we add optional of product
        //optional<Product> productOptional because as you see in that findById class there <T>
        //which let us add our object

        //Product product=productRepository.findById(productId).get();

        Optional<Product> productOptional=productRepository.findById(productId);
        if(productOptional.isPresent()){
            return productRepository.findById(productId).get();
        }else {
            throw new ProductNotFoundException("product not found");
        }
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public List<Product> getProductByDesc(String  description) {
        /*List<Product> products =productRepository.findAll();
        List<Product>matchedProduct=new ArrayList<>();
        for(Product product:products){
            if(product.getDescription().equals(description)){
                 matchedProduct.add(product);
            }
        }
        return matchedProduct;*/
        //here on top what we did we used the findAll method defined in the
        //jpaRepo and filter out the one we needed
        //while below we did is we created a custom method id jpa and directly used it
        //jpa will implement that to
        return productRepository.findAllByDescription(description);
    }
    public ProductProjection getProductByProjection(String name) {
        return productRepository.findFirstByName(name);
    }
    public FakeStoreProductDTO[] getALLProductsFromFakestore(){
        return fakeStoreClient.getAllProducts();
    }
    public FakeStoreProductDTO getProductById(int id){
        return fakeStoreClient.getProduct(id);
    }
    public FakeStoreProductDTO createProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        return fakeStoreClient.createProduct(fakeStoreProductDTO);
    }
    public FakeStoreProductDTO replaceProduct(FakeStoreProductDTO fakeStoreProductDTO,int id){
        return fakeStoreClient.replaceProductById(fakeStoreProductDTO,id);
    }
    public Boolean deleteProductById(int id){
        return fakeStoreClient.deleteProductById(id);
    }



}
