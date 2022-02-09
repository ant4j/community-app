package app.community.content.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import app.community.content.service.exception.ConflictContentException;
import app.community.common.util.ErrorResponse;
import static app.community.common.util.ErrorResponse.prepareErrorResponse;

@ControllerAdvice(basePackages = "app.community.content.controller")
public class ContentExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(ContentExceptionHandler.class);

	@ExceptionHandler(ConflictContentException.class)
	public ResponseEntity<ErrorResponse> handleConflictContentException(ConflictContentException e) {
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
