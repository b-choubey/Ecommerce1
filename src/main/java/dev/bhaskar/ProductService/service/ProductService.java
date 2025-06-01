package dev.bhaskar.ProductService.service;

import dev.bhaskar.ProductService.client.FakeStoreClient;
import dev.bhaskar.ProductService.dto.FakeStoreProductDTO;
import dev.bhaskar.ProductService.dto.ProductProjection;
import dev.bhaskar.ProductService.dto.ProductRequestDTO;
import dev.bhaskar.ProductService.exeception.CategoryNotFoundException;
import dev.bhaskar.ProductService.exeception.ProductNotFoundException;
import dev.bhaskar.ProductService.model.Category;
import dev.bhaskar.ProductService.model.Product;
import dev.bhaskar.ProductService.repository.CategoryRepository;
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
    //here we can not add categoryService as dependency  it will become circular dependency
    //so we will use categoryRepository
    @Autowired
    private CategoryRepository categoryRepository;

    //now we will create 4 methods
    public Product saveProduct(ProductRequestDTO productRequestDTO){
        //to save the product we will call save method of ProductRepository which is
        //implementing JpaRepository which contain save class
        Category savedCategory=categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(
                () -> new CategoryNotFoundException("Category not found with id " )
        );
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setQuantity(productRequestDTO.getQuantity());
        product.setRating(productRequestDTO.getRating());
        //now we have to add category, but we can not directly add category or set category as product
        // does not contain any category information so first we will check if category exist or not
        //before that we will save the product

        Product savedProduct=productRepository.save(product);

        savedCategory.getProducts().add(product);
        categoryRepository.save(savedCategory);
        return savedProduct;

    }
    public Product updateProduct(Product newproduct,int productId){
        Product savedProduct=getProduct(productId);
        //id should be same if i change the id it will ot be the same it will act like create product
        newproduct.setId(productId);
        Product updatedProduct= productRepository.save(newproduct);
        return updatedProduct;
    }
    public boolean deleteProduct(int productId){
        productRepository.deleteById(productId);
        return true;
    }
//    public List<Product> getAllProductByCategoryId(int categoryId){
//        //As we can not guarantee that categoryId that we are passing will be present so we are taking care
//        //of that with category service we @Autowired categoryService so that the object inside it will let us know
//        //if not present categoryId categoryService will take care of that
//        List<Product>product=categoryService.getAllProductBycategory(categoryId);
//        return product;
//    }
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
