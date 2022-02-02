package app.community.self.handler.exception;

public class NotFoundCommunityException extends CommunityException {
	private static final long serialVersionUID = 1L;

	public NotFoundCommunityException() {
		super();
	}

	public NotFoundCommunityException(String message) {
		super(message);
	}
}
