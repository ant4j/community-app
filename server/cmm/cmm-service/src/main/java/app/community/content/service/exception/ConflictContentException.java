package app.community.content.service.exception;

public class ConflictContentException extends ContentException {
	private static final long serialVersionUID = 1L;

	public ConflictContentException() {
		super();
	}

	public ConflictContentException(String message) {
		super(message);
	}
	
}
