package com.cognixia.jump.exception;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValid (MethodArgumentNotValidException ex, WebRequest request){

        StringBuilder errors = new StringBuilder("");
		for(FieldError fe : ex.getBindingResult().getFieldErrors()) {
			errors.append( "[" + fe.getField() + " : " + fe.getDefaultMessage() + "]; " );
		}
		
		// request.getDescription() -> details on the request (usually just includes the uri/url )
		ErrorDetails errorDetails = new ErrorDetails(new Date(), errors.toString(), request.getDescription(false) );

        return ResponseEntity.status(400).body(errorDetails);
    }
}
