package app.community.business.self.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.business.common.exception.ErrorMessage;
import app.community.business.common.exception.NotFoundException;
import app.community.business.common.exception.UnauthorizedException;
import app.community.business.self.model.CommunityAuthParamModel;
import app.community.business.self.model.CommunityModel;
import app.community.business.self.model.UsernameModel;
import app.community.persistence.community.model.CommunityAuthEntity;
import app.community.persistence.community.model.CommunityEntity;
import app.community.persistence.community.repository.CommunityAuthRepository;
import app.community.persistence.community.repository.CommunityRepository;

@Service
public class CommunityService {

	@Autowired
	private CommunityRepository communityRepository;

	@Autowired
	private CommunityAuthRepository communityAuthRepository;

	@Autowired
	private UsernameHandler usernameHandler;

	@Autowired
	private ModelMapper mapper;

	public CommunityModel getCommunity(String code) {
		Optional<CommunityEntity> optionalResult = communityRepository.findByCode(code);
		if (!optionalResult.isPresent()) {
			throw new NotFoundException(ErrorMessage.COMMUNITY_NOT_FOUND_MSG);
		}
		CommunityModel communityModel = mapper.map(optionalResult.get(), CommunityModel.class);
		return communityModel;
	}

	public UsernameModel authenticate(CommunityAuthParamModel communityAuthParamModel) {
		Optional<CommunityAuthEntity> optionalResult = communityAuthRepository
				.findById(communityAuthParamModel.getCommunityId());
		if (!optionalResult.isPresent()) {
			throw new NotFoundException(ErrorMessage.COMMUNITY_AUTHENTICATION_NOT_FOUND_MSG);
		}
		if (!communityAuthParamModel.getWatchword().equals(optionalResult.get().getWatchword())) {
			throw new UnauthorizedException(ErrorMessage.UNAUTHORIZED_MSG);
		}
		UsernameModel usernameModel = usernameHandler.detachUsername();
		return usernameModel;
	}

}
