package app.community.web.common.exception;

import static app.community.web.common.exception.model.ErrorResponse.prepareErrorResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import app.community.business.common.exception.ConflictException;
import app.community.business.common.exception.NotFoundException;
import app.community.business.common.exception.UnauthorizedException;
import app.community.web.common.exception.model.ErrorResponse;

@ControllerAdvice(basePackages = {
		"app.community.web.self.controller",
		"app.community.web.collection.controller",
		"app.community.web.content.controller"
})
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
		LOG.error(e.getMessage());
		ErrorResponse errorResponse = prepareErrorResponse(HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException e) {
		LOG.error(e.getMessage());
		ErrorResponse errorResponse = prepareErrorResponse(HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.getReasonPhrase(), e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ErrorResponse> handleConflictException(ConflictException e) {
		LOG.error(e.getMessage());
		ErrorResponse errorResponse = prepareErrorResponse(HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.getReasonPhrase(), e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e) {
		// LOG.error(e.getMessage());
		e.printStackTrace();
		ErrorResponse errorResponse = prepareErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
