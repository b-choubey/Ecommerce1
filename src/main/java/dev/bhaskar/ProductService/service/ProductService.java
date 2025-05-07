package dev.bhaskar.ProductService.service;

import dev.bhaskar.ProductService.client.FakeStoreClient;
import dev.bhaskar.ProductService.dto.FakeStoreProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class ProductService {
    //it will be dependent on fakestore client
    @Autowired
    private FakeStoreClient fakeStoreClient;


    public FakeStoreProductDTO[] getALLProductsFromFakestore(){
        return fakeStoreClient.getAllProducts();
    }
}
