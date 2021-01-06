package com.example.explore.exceptionhandler;

import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;

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

	@Bean
	public ErrorAttributes errorAttributes() {
		// Hide exception field in the return object
		return new DefaultErrorAttributes() {
			@Override
			public Map<String, Object> getErrorAttributes(WebRequest requestAttributes, boolean includeStackTrace) {
				@SuppressWarnings("deprecation")
				Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
				errorAttributes.remove("exception");
				return errorAttributes;
			}
		};
	}

	@ExceptionHandler(AccessDeniedException.class)
	public void handleAccessDeniedException(AccessDeniedException ex, HttpServletResponse res) throws IOException {
		res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
	}

	@ExceptionHandler(HttpServerErrorException.class)
	public void handleHttpServerErrorException(HttpServerErrorException ex, HttpServletResponse res)
			throws IOException {
		res.sendError(ex.getStatusCode().value(), ex.getMessage());
	}

	@ExceptionHandler(InsufficientAuthenticationException.class)
	public void handleInsufficientAuthenticationException(Exception ex, HttpServletResponse res) throws IOException {
		logger.error("Handled Insufficient Authentication Exception", ex);
		res.sendError(HttpStatus.FORBIDDEN.value(), "Insufficient Authentication");
	}

	@ExceptionHandler(Exception.class)
	public void handleException(Exception ex, HttpServletResponse res) throws IOException {
		logger.error("Handled Internal Error Exception", ex);
		res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong");
	}

}
