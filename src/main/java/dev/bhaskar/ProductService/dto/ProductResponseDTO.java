package dev.bhaskar.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private double productPrice;
    private String productName;
    private String productDescription;
    private double productRating;
    public ProductResponseDTO(double productPrice, String productName, String productDescription, double productRating) {
        this.productPrice = productPrice;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productRating = productRating;
    }

}
