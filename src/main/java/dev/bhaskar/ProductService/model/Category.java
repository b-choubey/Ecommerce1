package dev.bhaskar.ProductService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
@OneToMany
@JoinColumn(name = "category_id")//if you want mapping table don't give the  @join annotation
private List<Product> products;

}
