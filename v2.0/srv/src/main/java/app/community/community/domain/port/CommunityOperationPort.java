package app.community.community.domain.port;

import app.community.community.domain.model.CommunityAuthenticationTO;
import app.community.community.domain.model.CommunityTO;

public interface CommunityOperationPort {
	public CommunityTO getCommunity();
	public CommunityAuthenticationTO getCommunityAuthentication();
}
