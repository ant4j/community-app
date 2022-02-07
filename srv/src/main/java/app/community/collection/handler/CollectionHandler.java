package app.community.collection.handler;

import java.util.List;

import app.community.collection.controller.model.CollectionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.collection.controller.model.CollectionListDTO;
import app.community.collection.controller.model.CommunityParamDTO;
import app.community.collection.handler.mapper.CollectionMapper;
import app.community.collection.persistence.model.CollectionEntity;
import app.community.collection.persistence.repository.CollectionRepository;

@Service
public class CollectionHandler {
	
	@Autowired
	private CollectionRepository collectionRepository;
	
	public CollectionListDTO getCollections(CommunityParamDTO communityParamDTO) {
		List<CollectionEntity> entityList = collectionRepository.findAllByCommunityId(communityParamDTO.getCommunityId());
		CollectionListDTO collectionListDTO = new CollectionListDTO();
		if(entityList.isEmpty()) {
			collectionListDTO.setCollectionList(List.of());
		} else {
			CollectionMapper mapper = CollectionMapper.INSTANCE;
			List<CollectionDTO> collectionList = mapper.toDTO(entityList);
			collectionListDTO.setCollectionList(collectionList);
		}
		return collectionListDTO;
	}
}