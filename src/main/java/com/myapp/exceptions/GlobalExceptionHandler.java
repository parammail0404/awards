package com.myapp.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoRecordFoundException.class)
	//@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handleNoRecordFoundException(NoRecordFoundException ex) 
	{

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage("No Record Found.......");
		return errorResponse;
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex) {
		System.out.println("Bad Request");
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleEmployeeNotFound( EmployeeNotFoundException exception ) {
		return ResponseEntity .status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
}
