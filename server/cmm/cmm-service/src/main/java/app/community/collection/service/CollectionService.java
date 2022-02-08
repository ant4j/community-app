package app.community.collection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.collection.persistence.model.CollectionEntity;
import app.community.collection.persistence.repository.CollectionRepository;
import app.community.collection.service.mapper.CollectionMapper;
import app.community.collection.service.model.CollectionDTO;
import app.community.collection.service.model.CollectionListDTO;
import app.community.collection.service.model.CommunityParamDTO;

@Service
public class CollectionService {
	
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
