package dev.bhaskar.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDTO {
    private String message;
    private Integer code;
    public ErrorResponseDTO(String message, Integer code){
        this.message=message;
        this.code=code;
    }
}
