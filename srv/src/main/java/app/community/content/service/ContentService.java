package app.community.content.service;

import app.community.content.controller.model.*;
import app.community.content.persistence.model.ContentEntity;
import app.community.content.persistence.model.ProposalEntity;
import app.community.content.persistence.repository.ContentRepository;
import app.community.content.persistence.repository.ProposalRepository;
import app.community.content.service.exception.ConflictContentException;
import app.community.content.service.mapper.ContentMapper;
import app.community.content.service.mapper.ContentTextMapper;
import app.community.content.service.mapper.ProposalMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.content.persistence.repository.ContentTextRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

	@Autowired
	private ContentRepository contentRepository;

	@Autowired
	private ContentTextRepository contentTextRepository;

	@Autowired
	private ProposalRepository proposalRepository;

	public ContentListDTO getContents(ContentParamDTO contentParamDTO) {
		List<ContentEntity> entityList = contentRepository.findAllByCollectionId(contentParamDTO.getCollectionId());
		String collectionName = contentRepository.getCollectionNameByCollectionId(contentParamDTO.getCollectionId());
		ContentListDTO contentListDTO = new ContentListDTO();
		contentListDTO.setCollectionName(collectionName);
		if (entityList.isEmpty()) {
			contentListDTO.setContentList(List.of());
		} else {
			ContentMapper mapper = ContentMapper.INSTANCE;
			List<ContentDTO> contentList = mapper.toDTO(entityList);
			contentListDTO.setContentList(contentList);
		}
		return contentListDTO;
	}

	public ContentTextDTO getContentText(ContentTextParamDTO contentTextParamDTO) {
		String contentTitle = contentRepository.getTitleById(contentTextParamDTO.getContentId());
		ContentTextMapper mapper = ContentTextMapper.INSTANCE;
		ContentTextDTO contentTextDTO = mapper
				.toDTO(contentTextRepository.findById(contentTextParamDTO.getContentId()).get());
		contentTextDTO.setContentTitle(contentTitle);
		return contentTextDTO;
	}

	public void proposeContent(ProposalBodyDTO proposalBodyDTO) {
		Optional<ProposalEntity> optionalResult = proposalRepository.findLastProposal(proposalBodyDTO.getCommunityId());
		if (optionalResult.isPresent()) {
			ProposalEntity proposalEntity = optionalResult.get();
			if (proposalBodyDTO.getContentId().equals(proposalEntity.getContentId())) {
				throw new ConflictContentException("Content already proposed");
			}
		}
		ProposalMapper mapper = ProposalMapper.INSTANCE;
		ProposalEntity proposalEntity = mapper.toEntity(proposalBodyDTO);
		proposalEntity.setProposedOn(new Date());
		proposalRepository.save(proposalEntity);
	}

	public ProposalDTO getLastProposal(ProposalParamDTO proposalParamDTO) {
		Optional<ProposalEntity> optionalResult = proposalRepository
				.findLastProposal(proposalParamDTO.getCommunityId());
		if (optionalResult.isPresent()) {
			ProposalEntity proposalEntity = optionalResult.get();
			// TODO fare il controllo if(optional.isPresent())
			ContentEntity contentEntity = contentRepository.findById(proposalEntity.getContentId()).get();
			Integer collectionType = contentRepository.getCollectionTypeByCollectionId(contentEntity.getCollectionId());
			ProposalMapper mapper = ProposalMapper.INSTANCE;
			ProposalDTO proposalDTO = mapper.toDTO(proposalEntity);
			proposalDTO.setCollectionId(contentEntity.getCollectionId());
			proposalDTO.setCollectionType(collectionType);
			proposalDTO.setContentTitle(contentEntity.getTitle());
			proposalDTO.setIsPresent(true);
			return proposalDTO;
		}
		return new ProposalDTO();
	}
}
