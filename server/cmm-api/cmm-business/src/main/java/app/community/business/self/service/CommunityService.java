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
import app.community.persistence.self.model.CommunityAuthEntity;
import app.community.persistence.self.model.CommunityEntity;
import app.community.persistence.self.repository.CommunityAuthRepository;
import app.community.persistence.self.repository.CommunityRepository;

@Service
public class CommunityService {

	@Autowired
	private CommunityRepository communityRepository;

	@Autowired
	private CommunityAuthRepository communityAuthRepository;

	@Autowired
	private ModelMapper mapper;

	public CommunityModel getCommunityByCode(String code) {
		Optional<CommunityEntity> optionalResult = communityRepository.findByCode(code);
		if (!optionalResult.isPresent()) {
			throw new NotFoundException(ErrorMessage.COMMUNITY_NOT_FOUND_MSG);
		}
		CommunityModel communityModel = mapper.map(optionalResult.get(), CommunityModel.class);
		return communityModel;
	}

	public void checkAuthentication(Long communityId, CommunityAuthParamModel communityAuthParamModel) {
		Optional<CommunityAuthEntity> optionalResult = communityAuthRepository
				.findById(communityId);
		if (!optionalResult.isPresent()) {
			throw new NotFoundException(ErrorMessage.COMMUNITY_AUTHENTICATION_NOT_FOUND_MSG);
		}
		if (!communityAuthParamModel.getWatchword().equals(optionalResult.get().getWatchword())) {
			throw new UnauthorizedException(ErrorMessage.UNAUTHORIZED_MSG);
		}
	}

}
