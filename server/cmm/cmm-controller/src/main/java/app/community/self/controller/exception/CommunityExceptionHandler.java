package app.community.self.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import app.community.self.service.exception.NotFoundCommunityException;
import app.community.self.service.exception.UnauthorizedCommunityException;
import app.community.common.util.ErrorResponse;
import static app.community.common.util.ErrorResponse.prepareErrorResponse;

@ControllerAdvice(basePackages = "app.community.self.controller")
public class CommunityExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(CommunityExceptionHandler.class);

	@ExceptionHandler(NotFoundCommunityException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundCommunityException e) {
		LOG.error(e.getMessage());
		ErrorResponse errorResponse = prepareErrorResponse(HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UnauthorizedCommunityException.class)
	public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedCommunityException e) {
		LOG.error(e.getMessage());
		ErrorResponse errorResponse = prepareErrorResponse(HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.getReasonPhrase(), e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
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
