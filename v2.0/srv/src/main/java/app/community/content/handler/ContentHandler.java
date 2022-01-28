package app.community.content.handler;

import app.community.content.controller.model.*;
import app.community.content.handler.exception.ConflictContentException;
import app.community.content.handler.mapper.ContentMapper;
import app.community.content.handler.mapper.ProposalMapper;
import app.community.content.persistence.model.ContentEntity;
import app.community.content.persistence.model.ProposalEntity;
import app.community.content.persistence.repository.ContentRepository;
import app.community.content.persistence.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.content.handler.mapper.ContentTextMapper;
import app.community.content.persistence.repository.ContentTextRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContentHandler {

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
        ContentTextDTO contentTextDTO = mapper.toDTO(contentTextRepository.findById(contentTextParamDTO.getContentId()).get());
        contentTextDTO.setContentTitle(contentTitle);
        return contentTextDTO;
    }

    @Transactional
    public void proposeContent(ProposalParamDTO proposalParamDTO) {
        Optional<ProposalEntity> optionalResult = proposalRepository.findByCommunityId(proposalParamDTO.getCommunityId());
        if (optionalResult.isPresent()) {
            ProposalEntity proposalEntity = optionalResult.get();
            if (proposalParamDTO.getContentId().equals(proposalEntity.getContentId())) {
                throw new ConflictContentException("Content already proposed");
            } else {
                proposalEntity.setContentId(proposalParamDTO.getContentId());
                proposalEntity.setUsername(proposalParamDTO.getUsername());
                proposalEntity.setTimestamp(new Date());
                proposalRepository.save(proposalEntity);
            }
        } else {
            ProposalMapper mapper = ProposalMapper.INSTANCE;
            ProposalEntity proposalEntity = mapper.toEntity(proposalParamDTO);
            proposalEntity.setTimestamp(new Date());
            proposalRepository.save(proposalEntity);
        }
    }

    public ProposalDTO getProposal(ProposalParamDTO proposalParamDTO) {
        //recuperare proposal
        Optional<ProposalEntity> optionalResult = proposalRepository.findByCommunityId(proposalParamDTO.getCommunityId());
        if (optionalResult.isPresent()) {
            ProposalEntity proposalEntity = optionalResult.get();
            //recuperare content
            //TODO fare il controllo if(optional.isPresent())
            ContentEntity contentEntity = contentRepository.findById(proposalEntity.getContentId()).get();
            //recuperare collection
            Integer collectionType = contentRepository.getCollectionTypeByCollectionId(contentEntity.getCollectionId());
            ProposalMapper mapper = ProposalMapper.INSTANCE;
            ProposalDTO proposalDTO = mapper.toDTO(proposalEntity);
            proposalDTO.setContentTitle(contentEntity.getTitle());
            proposalDTO.setCollectionType(collectionType);
            proposalDTO.setIsPresent(true);
            return proposalDTO;
        }
        return new ProposalDTO();
    }
}
