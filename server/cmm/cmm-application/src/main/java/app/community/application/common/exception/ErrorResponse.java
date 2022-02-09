package app.community.application.common.exception;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorResponse {
	private Date timestamp = new Date();
	private Integer status;
	private String error;
	private String message;
	
    public static ErrorResponse prepareErrorResponse(Integer status, String error, String message) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(status);
        errorResponse.setError(error);
        errorResponse.setMessage(message);
        //"path": "/api/v1/content/text/44/1"
        return errorResponse;
    }
    
}
