package app.community.self.handler;

import java.util.Optional;

import app.community.self.controller.model.CommunityAuthenticationBodyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.community.self.controller.model.CommunityParamDTO;
import app.community.self.controller.model.CommunityDTO;
import app.community.self.controller.model.UsernameDTO;
import app.community.self.handler.exception.NotFoundCommunityException;
import app.community.self.handler.exception.UnauthorizedCommunityException;
import app.community.self.handler.mapper.CommunityMapper;
import app.community.self.persistence.model.CommunityAuthenticationEntity;
import app.community.self.persistence.model.CommunityEntity;
import app.community.self.persistence.repository.CommunityAuthenticationRepository;
import app.community.self.persistence.repository.CommunityRepository;
import app.community.self.persistence.repository.UserCounterRepository;

@Service
public class CommunityHandler {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private CommunityAuthenticationRepository communityAuthenticationRepository;

    @Autowired
    private UserCounterRepository userCounterRepository;

    public CommunityDTO getCommunity(CommunityParamDTO communityParamDTO) {
        Optional<CommunityEntity> optionalResult = communityRepository.findByCode(communityParamDTO.getCode());
        if (!optionalResult.isPresent()) {
            throw new NotFoundCommunityException("Community not found");
        }
        CommunityMapper mapper = CommunityMapper.INSTANCE;
        return mapper.toDTO(optionalResult.get());
    }

    @Transactional
    public UsernameDTO authenticate(CommunityAuthenticationBodyDTO communityAuthenticationBodyDTO) {
        Optional<CommunityAuthenticationEntity> optionalResult = communityAuthenticationRepository.findById(communityAuthenticationBodyDTO.getCommunityId());
        if (!optionalResult.isPresent()) {
            throw new NotFoundCommunityException("Community authentication not found");
        }
        if (communityAuthenticationBodyDTO.getWatchword().equals(optionalResult.get().getWatchword())) {
            Long counter = userCounterRepository.detachCounter();
            UsernameDTO usernameDTO = new UsernameDTO();
            usernameDTO.setUsername("user".concat(counter.toString()));
            return usernameDTO;
        } else {
            throw new UnauthorizedCommunityException("Unauthorized");
        }
    }

}
