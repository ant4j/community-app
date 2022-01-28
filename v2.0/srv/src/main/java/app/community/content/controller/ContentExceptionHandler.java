package app.community.content.controller;

import app.community.content.handler.exception.ConflictContentException;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice(basePackages = "app.community.content.controller")
public class ContentExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ContentExceptionHandler.class);

    @ExceptionHandler(ConflictContentException.class)
    public ResponseEntity<ErrorResponse> handleConflictContentException(ConflictContentException e) {
        LOG.error(e.getMessage());
        ErrorResponse errorResponse = prepareErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                e.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

	// ObjectOptimisticLockingFailureException //TODO da gestire, nel caso si modifica la risorsa (content) gia' esistente in contemporanea (update)
	// DataIntegrityViolationException //TODO da gestire, nel caso non esiste ancora la risorsa (content) (insert)

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        // LOG.error(e.getMessage());
		e.printStackTrace();
        ErrorResponse errorResponse = prepareErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse prepareErrorResponse(Integer status, String error, String message) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(status);
        errorResponse.setError(error);
        errorResponse.setMessage(message);
        //"path": "/api/v1/content/text/44/1"
        return errorResponse;
    }

    @Data
    class ErrorResponse {
        private Date timestamp = new Date();
        private Integer status;
        private String error;
        private String message;
    }
}
