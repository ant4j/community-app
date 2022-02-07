package app.community.self.service;

import java.util.Optional;

import app.community.self.controller.model.CommunityAuthenticationBodyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.self.controller.model.CommunityParamDTO;
import app.community.self.controller.model.CommunityDTO;
import app.community.self.controller.model.UsernameDTO;
import app.community.self.persistence.model.CommunityAuthenticationEntity;
import app.community.self.persistence.model.CommunityEntity;
import app.community.self.persistence.model.UserCodeEntity;
import app.community.self.persistence.repository.CommunityAuthenticationRepository;
import app.community.self.persistence.repository.CommunityRepository;
import app.community.self.persistence.repository.UserCodeRepository;
import app.community.self.persistence.repository.UserPoolRepository;
import app.community.self.service.exception.NotFoundCommunityException;
import app.community.self.service.exception.UnauthorizedCommunityException;
import app.community.self.service.handler.UsernameHandler;
import app.community.self.service.mapper.CommunityMapper;

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
    private UsernameHandler usernameHandler;

    public CommunityDTO getCommunity(CommunityParamDTO communityParamDTO) {
        Optional<CommunityEntity> optionalResult = communityRepository.findByCode(communityParamDTO.getCode());
        if (!optionalResult.isPresent()) {
            throw new NotFoundCommunityException("Community not found");
        }
        CommunityMapper mapper = CommunityMapper.INSTANCE;
        return mapper.toDTO(optionalResult.get());
    }

    public UsernameDTO authenticate(CommunityAuthenticationBodyDTO communityAuthenticationBodyDTO) {
        Optional<CommunityAuthenticationEntity> optionalResult = communityAuthenticationRepository.findById(communityAuthenticationBodyDTO.getCommunityId());
        if (!optionalResult.isPresent()) {
            throw new NotFoundCommunityException("Community authentication not found");
        }
        if (communityAuthenticationBodyDTO.getWatchword().equals(optionalResult.get().getWatchword())) {
            Long counter = userPoolRepository.detachUserCodeId();
            //TODO controllo optional.isPresent(), altrimenti sono finiti i code
            Optional<UserCodeEntity> optionalResult2 = userCodeRepository.findById(counter);
            return usernameHandler.buildUsername(optionalResult2.get().getCode());
        } else {
            throw new UnauthorizedCommunityException("Unauthorized");
        }
    }

}
