package app.community.content.handler;

import app.community.content.controller.model.*;
import app.community.content.handler.mapper.ContentMapper;
import app.community.content.persistence.model.ContentEntity;
import app.community.content.persistence.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.content.handler.mapper.ContentTextMapper;
import app.community.content.persistence.repository.ContentTextRepository;

import java.util.List;

@Service
public class ContentHandler {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ContentTextRepository contentTextRepository;

    public ContentListDTO getContents(ContentParamDTO contentParamDTO) {
        List<ContentEntity> entityList = contentRepository.findAllByCollectionId(contentParamDTO.getCollectionId());
        String collectionName = contentRepository.getCollectionNameByCollectionId(contentParamDTO.getCollectionId());
        ContentListDTO contentListDTO = new ContentListDTO();
        contentListDTO.setCollectionName(collectionName);
        if(entityList.isEmpty()) {
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
}
