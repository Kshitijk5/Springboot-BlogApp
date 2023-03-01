package com.example.demo.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetail> errorResponse(ResourceNotFoundException exception, WebRequest webRequest) {

		ErrorDetail detail = new ErrorDetail();
		detail.setTimestamp(new Date());
		detail.setMessage(exception.getMessage());
		detail.setDetails(webRequest.getDescription(false));

		return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler
	public ResponseEntity<Object> errorResponse(MethodArgumentNotValidException exception, WebRequest webRequest) {
		Map<String ,String > errors = new HashMap<>();
		
		for (ObjectError error : exception.getBindingResult().getAllErrors()) {
			String fieldName = ((FieldError)error).getField();
			String defaultMessage = error.getDefaultMessage();
			
			errors.put(fieldName, defaultMessage);
			
		}
		
		return  new ResponseEntity<>( errors,HttpStatus.BAD_REQUEST);

		
	}

}
