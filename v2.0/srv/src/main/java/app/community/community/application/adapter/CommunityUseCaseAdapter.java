package app.community.community.application.adapter;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.community.application.port.CommunityUseCasePort;
import app.community.community.domain.model.CommunityTO;
// import app.community.community.domain.port.CommunityOperationPort;

@Service
public class CommunityUseCaseAdapter implements CommunityUseCasePort {
	
	// @Autowired
	// private CommunityOperationPort communityOperationPort;

	@Override
	public CommunityTO getCommunity() {
		CommunityTO communityTO = new CommunityTO();
		communityTO.setId(1L);
		communityTO.setName("test");
		return communityTO;
	}

	@Override
	public void authenticate() {
		// TODO Auto-generated method stub
	}

}
