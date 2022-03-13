package app.community.business.content.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.business.common.exception.ConflictException;
import app.community.business.common.exception.ErrorMessage;
import app.community.business.content.model.ContentModel;
import app.community.business.content.model.ContentListModel;
import app.community.business.content.model.ContentTextAndTitleModel;
import app.community.business.content.model.LastProposalModel;
import app.community.business.content.model.ProposalParamModel;
import app.community.persistence.collection.repository.CollectionRepository;
import app.community.persistence.content.model.ContentEntity;
import app.community.persistence.content.model.ContentTextEntity;
import app.community.persistence.content.model.ProposalEntity;
import app.community.persistence.content.repository.ContentRepository;
import app.community.persistence.content.repository.ContentTextRepository;
import app.community.persistence.content.repository.ProposalRepository;

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

	@Autowired
	private ModelMapper mapper;
	
	public ContentListModel getContents( Long collectionId) {
		List<ContentEntity> contentEntityList = contentRepository.findAllByCollectionId(collectionId);
		String collectionName = collectionRepository.getCollectionNameByCollectionId(collectionId);
		ContentListModel contentListModel = new ContentListModel();
		contentListModel.setCollectionName(collectionName);
		List<ContentModel> contentList = contentEntityList.stream()
				.map(contentEntity -> mapper.map(contentEntity, ContentModel.class))
				.collect(Collectors.toList());
		contentListModel.setContentList(contentList);
		return contentListModel;
	}

	public ContentTextAndTitleModel getContentText( Long contentId) {
		String contentTitle = contentRepository.getTitleById(contentId);
		// TODO fare il controllo if(optional.isPresent())
		ContentTextEntity contentTextEntity = contentTextRepository.findById(contentId).get();
		ContentTextAndTitleModel contentTextAndTitleModel = mapper.map(contentTextEntity, ContentTextAndTitleModel.class);
		contentTextAndTitleModel.setContentTitle(contentTitle);
		return contentTextAndTitleModel;
	}

	public void proposeContent( ProposalParamModel proposalParamModel) {
		Optional<ProposalEntity> optionalResult = proposalRepository
				.findLastProposal(proposalParamModel.getCommunityId());
		if (optionalResult.isPresent()) {
			ProposalEntity proposalEntity = optionalResult.get();
			if (proposalParamModel.getContentId().equals(proposalEntity.getContentId())) {
				throw new ConflictException(ErrorMessage.CONTENT_ALREADY_PROPOSED_MSG);
			}
		}
		ProposalEntity proposalEntity = new ProposalEntity();
		proposalEntity.setCommunityId(proposalParamModel.getCommunityId());
		proposalEntity.setContentId(proposalParamModel.getContentId());
		proposalEntity.setUsername(proposalParamModel.getUsername());
		proposalEntity.setProposedOn(LocalDateTime.now());
		proposalRepository.save(proposalEntity);
	}

	public LastProposalModel getLastProposal( Long communityId) {
		Optional<ProposalEntity> optionalResult = proposalRepository.findLastProposal(communityId);
		LastProposalModel lastProposalModel = new LastProposalModel();
		if (optionalResult.isPresent()) {
			// TODO fare il controllo if(optional.isPresent())
			ProposalEntity proposalEntity = optionalResult.get();
			// TODO fare il controllo if(optional.isPresent())
			ContentEntity contentEntity = contentRepository.findById(proposalEntity.getContentId()).get();
			Integer collectionType = collectionRepository
					.getCollectionTypeByCollectionId(contentEntity.getCollectionId());
			lastProposalModel.setId(proposalEntity.getId());
			lastProposalModel.setCommunityId(proposalEntity.getCommunityId());
			lastProposalModel.setCollectionId(contentEntity.getCollectionId());
			lastProposalModel.setCollectionType(collectionType);
			lastProposalModel.setContentId(proposalEntity.getContentId());
			lastProposalModel.setContentTitle(contentEntity.getTitle());
			lastProposalModel.setUsername(proposalEntity.getUsername());
			lastProposalModel.setProposedOn(proposalEntity.getProposedOn());
			lastProposalModel.setIsPresent(true);
		}
		return lastProposalModel;
	}
	
}
