package com.example.calucalteage.configuration;

import com.example.calucalteage.exception.ErrorResponse;
import com.example.calucalteage.exception.NotFoundUserException;
import com.example.calucalteage.model.response.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class UserControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundUserException.class)
	@ResponseStatus(BAD_REQUEST)
	public ApiError handleUserException(NotFoundUserException userException) {
		return userException::getMessage;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers,
																  HttpStatusCode status,
																  WebRequest request) {
		return ex.getBindingResult().getAllErrors()
				.stream()
				.map(ObjectError::getDefaultMessage)
				.collect(collectingAndThen(
						toList(),
						details -> ResponseEntity.badRequest()
								.body(new ErrorResponse("Have no user with this ID", details))
				));
	}
}
