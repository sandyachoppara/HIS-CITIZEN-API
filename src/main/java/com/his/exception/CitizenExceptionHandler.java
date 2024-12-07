package com.his.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class CitizenExceptionHandler {
	
	// CITIZEN Custom Exception
	@ExceptionHandler(value=CitizenException.class)
	public ResponseEntity<CitizenApiError> handleEdException(CitizenException e) {
		CitizenApiError apiError=new CitizenApiError("CITIZEN-API-02", e.getMessage(), new Date());
		return new ResponseEntity<CitizenApiError>(apiError,HttpStatus.BAD_REQUEST);
	};
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<CitizenApiError> handleException(Exception e) {
		CitizenApiError apiError=new CitizenApiError("CITIZEN-API-02", e.getMessage(), new Date());
		return new ResponseEntity<CitizenApiError>(apiError,HttpStatus.BAD_REQUEST);
	};

}
