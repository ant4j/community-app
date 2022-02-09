package app.community.self.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.self.persistence.model.CommunityAuthenticationEntity;
import app.community.self.persistence.model.CommunityEntity;
import app.community.self.persistence.model.UserCodeEntity;
import app.community.self.persistence.model.UserPoolEntity;
import app.community.self.persistence.repository.CommunityAuthenticationRepository;
import app.community.self.persistence.repository.CommunityRepository;
import app.community.self.persistence.repository.UserCodeRepository;
import app.community.self.persistence.repository.UserPoolRepository;
import app.community.self.service.exception.NotFoundCommunityException;
import app.community.self.service.exception.UnauthorizedCommunityException;
import app.community.self.service.mapper.CommunityMapper;
import app.community.self.service.model.CommunityAuthenticationBodyDTO;
import app.community.self.service.model.CommunityDTO;
import app.community.self.service.model.CommunityParamDTO;
import app.community.self.service.model.UsernameDTO;

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
            Long userCodeId = userPoolRepository.save(new UserPoolEntity()).getUserCodeId();
            //TODO controllo optional.isPresent(), altrimenti sono finiti i code
            UserCodeEntity userCodeEntity = userCodeRepository.findById(userCodeId).get();
            return usernameComponent.buildUsername(userCodeEntity.getCode());
        } else {
            throw new UnauthorizedCommunityException("Unauthorized");
        }
    }

}
