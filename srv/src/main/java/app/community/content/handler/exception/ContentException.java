package app.community.content.handler.exception;

public class ContentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ContentException() {
		super();
	}

	public ContentException(String message) {
		super(message);
	}
	
}
