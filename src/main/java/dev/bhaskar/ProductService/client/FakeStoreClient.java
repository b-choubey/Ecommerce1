package dev.bhaskar.ProductService.client;

import dev.bhaskar.ProductService.dto.FakeStoreProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

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
    public FakeStoreProductDTO getProduct(int id){
        String getProductUrl="https://fakestoreapi.com/products/" + id;
        FakeStoreProductDTO response=restTemplate.getForObject(getProductUrl,FakeStoreProductDTO.class);
        return response;
    }
    public FakeStoreProductDTO createProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        String createProduct="https://fakestoreapi.com/products";
        FakeStoreProductDTO response=requestForObject(createProduct,HttpMethod.POST,fakeStoreProductDTO,FakeStoreProductDTO.class);
        return response;
    }
    public FakeStoreProductDTO replaceProductById(FakeStoreProductDTO fakeStoreProductDTO,int id){
        String replaceProductUrl="https://fakestoreapi.com/products/" + id;
        FakeStoreProductDTO responce=requestForObject(replaceProductUrl,HttpMethod.PUT,fakeStoreProductDTO,FakeStoreProductDTO.class);
        return responce;
    }
    public Boolean deleteProductById(int id){
        String deleteProductUrl="https://fakestoreapi.com/products"+id;
        try{
            requestForObject(deleteProductUrl,HttpMethod.DELETE,null,FakeStoreProductDTO.class);
            return true;
        }catch(Exception e){
            //System.out.println(e);
            return false;
        }
    }
    private <T> T requestForObject(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor(responseType, restTemplate.getMessageConverters());
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, (Object[])uriVariables);
    }
    // what we did here is as in restTemplate we don't have putForObject so we copied postForObject method form restTemplate and then we just made it genric
    //we replace all this which is calling object ro its class. and the main thing which makes difference httpMethod.put or post or get we just
    //passed that as a variable. and used same in all method removing restTemplate. thing
    // for example purpose i have created in both the way.

}
