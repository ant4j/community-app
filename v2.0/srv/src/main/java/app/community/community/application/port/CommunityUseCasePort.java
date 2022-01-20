package app.community.community.application.port;

import app.community.community.domain.model.CommunityTO;

public interface CommunityUseCasePort {
	public CommunityTO getCommunity();
	public void authenticate();
}
