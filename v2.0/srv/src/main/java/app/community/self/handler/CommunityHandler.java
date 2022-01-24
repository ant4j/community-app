package app.community.self.handler;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.community.self.controller.model.CommunityAuthenticationTO;
import app.community.self.controller.model.CommunityCodeTO;
import app.community.self.controller.model.CommunityTO;
import app.community.self.controller.model.UsernameTO;
import app.community.self.handler.mapper.CommunityMapper;
import app.community.self.persistence.CommunityAuthenticationRepository;
import app.community.self.persistence.CommunityRepository;
import app.community.self.persistence.UserCounterRepository;
import app.community.self.persistence.model.CommunityAuthenticationEntity;
import app.community.self.persistence.model.CommunityEntity;

@Service
public class CommunityHandler {

	@Autowired
	private CommunityRepository communityRepository;

	@Autowired
	private CommunityAuthenticationRepository communityAuthenticationRepository;

	@Autowired
	private UserCounterRepository userCounterRepository;

	public CommunityTO getCommunity(CommunityCodeTO communityCodeTO) {
		Optional<CommunityEntity> optionalResult = communityRepository.findByCode(communityCodeTO.getCode());
		if (!optionalResult.isPresent()) {
			throw new RuntimeException("NOT FOUND");// TODO sistemare eccezione
		}
		CommunityMapper mapper = CommunityMapper.INSTANCE;
		return mapper.toTO(optionalResult.get());
	}

	@Transactional
	public UsernameTO authenticate(CommunityAuthenticationTO communityAuthenticationTO) {
		Optional<CommunityAuthenticationEntity> optionalResult = communityAuthenticationRepository
				.findById(communityAuthenticationTO.getCommunityId());
		if (!optionalResult.isPresent()) {
			throw new RuntimeException("NOT FOUND");// TODO sistemare eccezione
		}
		if (communityAuthenticationTO.getWatchword().equals(optionalResult.get().getWatchword())) {
			Long counter = userCounterRepository.detachCounter();
			UsernameTO usernameTO = new UsernameTO();
			usernameTO.setUsername("user".concat(counter.toString()));
			return usernameTO;
		} else {
			throw new RuntimeException("UNAUTHORIZED");// TODO sistemare eccezione
		}
	}

}
