package app.community.business.content.service;

import app.community.business.content.exception.ConflictContentException;
import app.community.business.content.mapper.ContentMapper;
import app.community.business.content.mapper.ContentTextMapper;
import app.community.business.content.mapper.ProposalMapper;
import app.community.business.content.model.*;
import app.community.persistence.collection.repository.CollectionRepository;
import app.community.persistence.content.model.ContentEntity;
import app.community.persistence.content.model.ContentTextEntity;
import app.community.persistence.content.model.ProposalEntity;
import app.community.persistence.content.repository.ContentRepository;
import app.community.persistence.content.repository.ContentTextRepository;
import app.community.persistence.content.repository.ProposalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

	@Autowired
	private ContentRepository contentRepository;

	@Autowired
	private CollectionRepository collectionRepository;

	@Autowired
	private ContentTextRepository contentTextRepository;

	@Autowired
	private ProposalRepository proposalRepository;

	public ContentListDTO getContents(Long collectionId) {
		List<ContentEntity> contentEntityList = contentRepository.findAllByCollectionId(collectionId);
		String collectionName = collectionRepository.getCollectionNameByCollectionId(collectionId);
		ContentListDTO contentListDTO = new ContentListDTO();
		contentListDTO.setCollectionName(collectionName);
		if (contentEntityList.isEmpty()) {
			contentListDTO.setContentList(List.of());
		} else {
			ContentMapper mapper = ContentMapper.INSTANCE;
			List<ContentDTO> contentList = mapper.toDTO(contentEntityList);
			contentListDTO.setContentList(contentList);
		}
		return contentListDTO;
	}

	public ContentTextDTO getContentText(Long contentId) {
		String contentTitle = contentRepository.getTitleById(contentId);
		// TODO fare il controllo if(optional.isPresent())
		ContentTextEntity contentTextEntity = contentTextRepository.findById(contentId).get();
		ContentTextMapper mapper = ContentTextMapper.INSTANCE;
		ContentTextDTO contentTextDTO = mapper.toDTO(contentTextEntity);
		contentTextDTO.setContentTitle(contentTitle);
		return contentTextDTO;
	}

	public void proposeContent(ProposalParamDTO proposalParamDTO) {
		Optional<ProposalEntity> optionalResult = proposalRepository
				.findLastProposal(proposalParamDTO.getCommunityId());
		if (optionalResult.isPresent()) {
			ProposalEntity proposalEntity = optionalResult.get();
			if (proposalParamDTO.getContentId().equals(proposalEntity.getContentId())) {
				//TODO censire il messaggio d'errore centralmente in una classe
				throw new ConflictContentException("Content already proposed");
			}
		}
		ProposalMapper mapper = ProposalMapper.INSTANCE;
		ProposalEntity proposalEntity = mapper.toEntity(proposalParamDTO);
		proposalEntity.setProposedOn(new Date());
		proposalRepository.save(proposalEntity);
	}

	public ProposalDTO getLastProposal(Long communityId) {
		Optional<ProposalEntity> optionalResult = proposalRepository.findLastProposal(communityId);
		if (optionalResult.isPresent()) {
			// TODO fare il controllo if(optional.isPresent())
			ProposalEntity proposalEntity = optionalResult.get();
			// TODO fare il controllo if(optional.isPresent())
			ContentEntity contentEntity = contentRepository.findById(proposalEntity.getContentId()).get();
			Integer collectionType = collectionRepository
					.getCollectionTypeByCollectionId(contentEntity.getCollectionId());
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
