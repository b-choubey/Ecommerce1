package dev.bhaskar.ProductService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
//always try to reduce the db call to improve the performance
@Getter
@Setter
@Entity
public class Category extends BaseModel{
@OneToMany(fetch = FetchType.EAGER)//default fetch type is eager loading
@JoinColumn(name = "category_id")//if you want mapping table don't give the  @join annotation
private List<Product> products;

}
