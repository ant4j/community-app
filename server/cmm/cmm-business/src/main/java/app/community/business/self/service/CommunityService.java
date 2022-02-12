package app.community.business.self.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.business.self.exception.NotFoundCommunityException;
import app.community.business.self.exception.UnauthorizedCommunityException;
import app.community.business.self.mapper.CommunityMapper;
import app.community.business.self.model.CommunityAuthenticationParamDTO;
import app.community.business.self.model.CommunityDTO;
import app.community.business.self.model.UsernameDTO;
import app.community.persistence.self.model.CommunityAuthenticationEntity;
import app.community.persistence.self.model.CommunityEntity;
import app.community.persistence.self.model.UserCodeEntity;
import app.community.persistence.self.model.UserPoolEntity;
import app.community.persistence.self.repository.CommunityAuthenticationRepository;
import app.community.persistence.self.repository.CommunityRepository;
import app.community.persistence.self.repository.UserCodeRepository;
import app.community.persistence.self.repository.UserPoolRepository;

@Service
public class CommunityService {

	@Autowired
	private CommunityRepository communityRepository;

	@Autowired
	private CommunityAuthenticationRepository communityAuthenticationRepository;

	@Autowired
	private UserPoolRepository userPoolRepository;

	@Autowired
	private UserCodeRepository userCodeRepository;

	@Autowired
	private UsernameComponent usernameComponent;

	public CommunityDTO getCommunity(String code) {
		Optional<CommunityEntity> optionalResult = communityRepository.findByCode(code);
		if (!optionalResult.isPresent()) {
			//TODO censire il messaggio d'errore centralmente in una classe
			throw new NotFoundCommunityException("Community not found");
		}
		CommunityMapper mapper = CommunityMapper.INSTANCE;
		return mapper.toDTO(optionalResult.get());
	}

	public UsernameDTO authenticate(CommunityAuthenticationParamDTO communityAuthenticationParamDTO) {
		Optional<CommunityAuthenticationEntity> optionalResult = communityAuthenticationRepository
				.findById(communityAuthenticationParamDTO.getCommunityId());
		if (!optionalResult.isPresent()) {
			//TODO censire il messaggio d'errore centralmente in una classe
			throw new NotFoundCommunityException("Community authentication not found");
		}
		if (communityAuthenticationParamDTO.getWatchword().equals(optionalResult.get().getWatchword())) {
			Long userCodeId = userPoolRepository.save(new UserPoolEntity()).getUserCodeId();
			// TODO controllo optional.isPresent(), altrimenti sono finiti i code
			UserCodeEntity userCodeEntity = userCodeRepository.findById(userCodeId).get();
			return usernameComponent.buildUsername(userCodeEntity.getCode());
		} else {
			//TODO censire il messaggio d'errore centralmente in una classe
			throw new UnauthorizedCommunityException("Unauthorized");
		}
	}

}
