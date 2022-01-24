package app.community.community.domain.port;

import app.community.community.domain.model.CommunityAuthenticationTO;
import app.community.community.domain.model.CommunityTO;

public interface CommunityManagementPort {
	public CommunityTO createCommunity(CommunityTO communityTO);
	public CommunityTO getCommunity(String code);
	public CommunityAuthenticationTO getCommunityAuthentication(Long communityId);
}
