package com.klu.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotFoundException(MethodArgumentNotValidException e){
		Map<String,String> errors = new HashMap<>();
		e.getBindingResult().getFieldErrors()
					.forEach(student -> errors.put(student.getField(), student.getDefaultMessage()));
		
		return errors;
												
	}
	
	  @ExceptionHandler(Exception.class)
	  public String handleInternalServerException(Exception e) {
	    return e.getMessage();
	  }
}
