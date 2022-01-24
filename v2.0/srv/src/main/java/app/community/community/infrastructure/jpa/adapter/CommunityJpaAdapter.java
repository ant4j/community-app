package app.community.community.infrastructure.jpa.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.community.domain.model.CommunityAuthenticationTO;
import app.community.community.domain.model.CommunityTO;
import app.community.community.domain.port.CommunityManagementPort;
import app.community.community.infrastructure.jpa.adapter.mapper.CommunityAuthenticationMapper;
import app.community.community.infrastructure.jpa.adapter.mapper.CommunityMapper;
import app.community.community.infrastructure.jpa.repository.CommunityAuthenticationRepository;
import app.community.community.infrastructure.jpa.repository.CommunityRepository;

@Service
public class CommunityJpaAdapter implements CommunityManagementPort {

	@Autowired
	private CommunityRepository communityRepository;

	@Autowired
	private CommunityAuthenticationRepository communityAuthenticationRepository;

	@Override
	public CommunityTO createCommunity(CommunityTO communityTO) {
		CommunityMapper mapper = CommunityMapper.INSTANCE;
		return mapper.toModel(communityRepository.save(mapper.toEntity(communityTO)));
	}

	@Override
	public CommunityTO getCommunity(String code) {
		CommunityMapper mapper = CommunityMapper.INSTANCE;
		return mapper.toModel(communityRepository.findByCode(code).get());
	}

	@Override
	public CommunityAuthenticationTO getCommunityAuthentication(Long communityId) {
		CommunityAuthenticationMapper mapper = CommunityAuthenticationMapper.INSTANCE;
		return mapper.toModel(communityAuthenticationRepository.findById(communityId).get());
	}

}
