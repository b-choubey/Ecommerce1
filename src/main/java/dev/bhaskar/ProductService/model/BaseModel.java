package dev.bhaskar.ProductService.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    //as id is primary key for both category and product
    //But we have taken it into baseclass then we have annotated,
    //this class so product and category will know that id is their
    //primary key with @MappedSuperclass
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    //these are called audit it will have information who created when
    //created so later on when we have data discrepancy issue we will check these
    //things
    //**for every model we have to do that.
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
