package com.example.explore.exceptionhandler;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestControllerAdvice {

	private final static Logger logger = LoggerFactory.getLogger(RestControllerAdvice.class);

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public String returnClientSideError(NoSuchElementException e) {
		logger.error("Unable to complete transaction", e);
		return e.getLocalizedMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RuntimeException.class)
	public String return400(RuntimeException ex) {
		logger.error("Unable to complete transaction", ex);
		return ex.getMessage();
	}

}
