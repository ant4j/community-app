package app.community.community.application.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.community.application.port.CommunityServicePort;
import app.community.community.domain.model.CommunityAuthenticationTO;
import app.community.community.domain.model.CommunityTO;
import app.community.community.domain.port.CommunityManagementPort;

@Service
public class CommunityServiceAdapter implements CommunityServicePort {

	@Autowired
	private CommunityManagementPort communityManagement;

	@Override
	public CommunityTO createCommunity(CommunityTO communityTO) {
		return communityManagement.createCommunity(communityTO);
	}

	@Override
	public CommunityTO getCommunity(String code) {
		return communityManagement.getCommunity(code);
	}

	@Override
	public void authenticate(CommunityAuthenticationTO communityAuthenticationTO) {
		CommunityAuthenticationTO communityAuthenticationTO2 = communityManagement
				.getCommunityAuthentication(communityAuthenticationTO.getCommunityId());
		if (communityAuthenticationTO.getWatchword().equals(communityAuthenticationTO2.getWatchword())) {

		} else {
			throw new RuntimeException();
		}
	}

}
