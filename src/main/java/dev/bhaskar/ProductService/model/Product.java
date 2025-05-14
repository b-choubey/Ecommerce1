package dev.bhaskar.ProductService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity // it makes different from FakeStoreProductDTO as this annotation tells spring to create table
public class Product extends BaseModel {
    
    private double price;
    private int quantity;
    private double rating;

}
