package com.ecom.wishlist.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
        super("Resource not found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
