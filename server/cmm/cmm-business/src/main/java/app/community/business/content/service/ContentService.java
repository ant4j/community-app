package app.community.business.content.service;

import app.community.business.content.exception.ConflictContentException;
import app.community.business.content.mapper.ContentMapper;
import app.community.business.content.mapper.ContentTextMapper;
import app.community.business.content.mapper.ProposalMapper;
import app.community.domain.content.model.*;
import app.community.persistence.collection.repository.CollectionRepository;
import app.community.persistence.content.model.ContentEntity;
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

	public ContentListModel getContents(Long collectionId) {
		List<ContentEntity> entityList = contentRepository.findAllByCollectionId(collectionId);
		String collectionName = collectionRepository.getCollectionNameByCollectionId(collectionId);
		ContentListModel contentListModel = new ContentListModel();
		contentListModel.setCollectionName(collectionName);
		if (entityList.isEmpty()) {
			contentListModel.setContentList(List.of());
		} else {
			ContentMapper mapper = ContentMapper.INSTANCE;
			List<ContentModel> contentList = mapper.toModel(entityList);
			contentListModel.setContentList(contentList);
		}
		return contentListModel;
	}

	public ContentTextModel getContentText(Long contentId) {
		String contentTitle = contentRepository.getTitleById(contentId);
		ContentTextMapper mapper = ContentTextMapper.INSTANCE;
		ContentTextModel contentTextModel = mapper.toModel(contentTextRepository.findById(contentId).get());
		contentTextModel.setContentTitle(contentTitle);
		return contentTextModel;
	}

	public void proposeContent(ProposalParamModel proposalParamModel) {
		Optional<ProposalEntity> optionalResult = proposalRepository
				.findLastProposal(proposalParamModel.getCommunityId());
		if (optionalResult.isPresent()) {
			ProposalEntity proposalEntity = optionalResult.get();
			if (proposalParamModel.getContentId().equals(proposalEntity.getContentId())) {
				throw new ConflictContentException("Content already proposed");
			}
		}
		ProposalMapper mapper = ProposalMapper.INSTANCE;
		ProposalEntity proposalEntity = mapper.toEntity(proposalParamModel);
		proposalEntity.setProposedOn(new Date());
		proposalRepository.save(proposalEntity);
	}

	public ProposalModel getLastProposal(Long communityId) {
		Optional<ProposalEntity> optionalResult = proposalRepository.findLastProposal(communityId);
		if (optionalResult.isPresent()) {
			// TODO fare il controllo if(optional.isPresent())
			ProposalEntity proposalEntity = optionalResult.get();
			ContentEntity contentEntity = contentRepository.findById(proposalEntity.getContentId()).get();
			Integer collectionType = collectionRepository
					.getCollectionTypeByCollectionId(contentEntity.getCollectionId());
			ProposalMapper mapper = ProposalMapper.INSTANCE;
			ProposalModel proposalModel = mapper.toModel(proposalEntity);
			proposalModel.setCollectionId(contentEntity.getCollectionId());
			proposalModel.setCollectionType(collectionType);
			proposalModel.setContentTitle(contentEntity.getTitle());
			proposalModel.setIsPresent(true);
			return proposalModel;
		}
		return new ProposalModel();
	}
}
