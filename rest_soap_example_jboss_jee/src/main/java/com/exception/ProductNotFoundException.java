package com.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4462468768833817178L;

	public ProductNotFoundException(String name){
        super(name+" nie znaleziono");
    }
	
	public ProductNotFoundException(Long id){
        super(String.format("Product o id=%d nie znaleziono",id));
    }
}
