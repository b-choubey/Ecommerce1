package dev.bhaskar.ProductService.dto;

//Projection is interface projecting of an object contain subset of column /attribute for a model
//rule we should follow is get.(attribute_name) for rest check notes
public interface ProductProjection {
    String getName();
    String getDescription();
}
