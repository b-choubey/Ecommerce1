package dev.bhaskar.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDTO {
    private String name;
    private String description;

    public CategoryResponseDTO(String name, String description) {
        this.name = "name";
        this.description = "description";
    }

}
