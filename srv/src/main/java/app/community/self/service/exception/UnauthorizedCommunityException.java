package app.community.self.service.exception;

public class UnauthorizedCommunityException extends CommunityException {
	private static final long serialVersionUID = 1L;

	public UnauthorizedCommunityException() {
		super();
	}

	public UnauthorizedCommunityException(String message) {
		super(message);
	}
}
