package dev.bhaskar.ProductService.exeception;

import dev.bhaskar.ProductService.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//it will advise controller
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value=ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException ex){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
    return new ResponseEntity<>(errorResponseDTO,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value=CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCategoryNotFoundException(CategoryNotFoundException ex){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value=DuplicateCategoryNameException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicateCategoryNameException(DuplicateCategoryNameException ex){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.NOT_FOUND);
    }
}
