package app.community.business.collection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.business.collection.mapper.CollectionMapper;
import app.community.business.collection.model.CollectionListDTO;
import app.community.business.collection.model.CollectionDTO;
import app.community.persistence.collection.model.CollectionEntity;
import app.community.persistence.collection.repository.CollectionRepository;

@Service
public class CollectionService {

	@Autowired
	private CollectionRepository collectionRepository;

	public CollectionListDTO getCollections(Long communityId) {
		List<CollectionEntity> entityList = collectionRepository.findAllByCommunityId(communityId);
		CollectionListDTO collectionListDTO = new CollectionListDTO();
		if (entityList.isEmpty()) {
			collectionListDTO.setCollectionList(List.of());
		} else {
			CollectionMapper mapper = CollectionMapper.INSTANCE;
			List<CollectionDTO> collectionList = mapper.toDTO(entityList);
			collectionListDTO.setCollectionList(collectionList);
		}
		return collectionListDTO;
	}
}
