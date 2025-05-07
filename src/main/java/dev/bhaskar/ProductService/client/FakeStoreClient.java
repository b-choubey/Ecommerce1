package dev.bhaskar.ProductService.client;

import dev.bhaskar.ProductService.dto.FakeStoreProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/*
 component i am using if you remember whenever somewhere else as dependency you
 have to make sure this object present in ioc container
 and fakeStore is neither repo neither controller thats why we using component

 */
@Component
public class FakeStoreClient {
    //here restTemplate is dependency for my fakeStoreClient to take care
    //that dependency we use autowired and autowired need @component to work
    //to happen that dependency the object needs to be present
    //how we make sure the object is present we write component, service annotation

    @Autowired
    private RestTemplate restTemplate;
    public FakeStoreProductDTO[] getAllProducts(){
        String getAllProductsUrl = "https://fakestoreapi.com/products";
        FakeStoreProductDTO[] response= restTemplate.getForObject(getAllProductsUrl, FakeStoreProductDTO[].class);
        return response;
    }
}
