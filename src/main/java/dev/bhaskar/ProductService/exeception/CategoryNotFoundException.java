package dev.bhaskar.ProductService.exeception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException() {
    }
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
