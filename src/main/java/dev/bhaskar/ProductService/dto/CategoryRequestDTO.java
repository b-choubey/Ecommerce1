package dev.bhaskar.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {
    private String categoryName;
    private String categoryDescription;
    public CategoryRequestDTO(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
}
//Question is we created two dto for same thing one is categoryResponseDTO and one categoryRequestDTO
//having same thing why
//Later on if we use same DTO in all places if some change come into input i have to change many things
//having both different we can change one it will take care