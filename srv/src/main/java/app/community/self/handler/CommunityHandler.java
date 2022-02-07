package app.community.self.handler;

import java.util.Optional;

import app.community.self.controller.model.CommunityAuthenticationBodyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.self.controller.model.CommunityParamDTO;
import app.community.self.controller.model.CommunityDTO;
import app.community.self.controller.model.UsernameDTO;
import app.community.self.handler.exception.NotFoundCommunityException;
import app.community.self.handler.exception.UnauthorizedCommunityException;
import app.community.self.handler.mapper.CommunityMapper;
import app.community.self.persistence.model.CommunityAuthenticationEntity;
import app.community.self.persistence.model.CommunityEntity;
import app.community.self.persistence.model.UserCodeEntity;
import app.community.self.persistence.repository.CommunityAuthenticationRepository;
import app.community.self.persistence.repository.CommunityRepository;
import app.community.self.persistence.repository.UserCodeRepository;
import app.community.self.persistence.repository.UserPoolRepository;

@Service
public class CommunityHandler {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private CommunityAuthenticationRepository communityAuthenticationRepository;

    @Autowired
    private UserPoolRepository userPoolRepository;
    
    @Autowired
    private UserCodeRepository userCodeRepository;    
    
    @Autowired
    private UsernameBuilder usernameBuilder;

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
            return usernameBuilder.build(optionalResult2.get().getCode());
        } else {
            throw new UnauthorizedCommunityException("Unauthorized");
        }
    }

}
