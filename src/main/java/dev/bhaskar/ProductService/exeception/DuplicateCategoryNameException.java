package dev.bhaskar.ProductService.exeception;

public class DuplicateCategoryNameException extends RuntimeException{
    public DuplicateCategoryNameException(){

    }
    public DuplicateCategoryNameException(String message){
        super(message);
    }
}
