package app.community.community.application.port;

import app.community.community.domain.model.CommunityAuthenticationTO;
import app.community.community.domain.model.CommunityTO;

public interface CommunityServicePort {
	public CommunityTO createCommunity(CommunityTO communityTO);
	public CommunityTO getCommunity(String code);
	public void authenticate(CommunityAuthenticationTO communityAuthenticationTO);
}
