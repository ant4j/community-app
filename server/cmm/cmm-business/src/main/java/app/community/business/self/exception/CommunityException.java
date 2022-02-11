package app.community.business.self.exception;

public class CommunityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CommunityException() {
		super();
	}

	public CommunityException(String message) {
		super(message);
	}
	
}