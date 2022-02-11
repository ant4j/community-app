package app.community.business.collection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.business.collection.mapper.CollectionMapper;
import app.community.domain.collection.model.CollectionModel;
import app.community.domain.collection.model.CollectionListModel;
import app.community.persistence.collection.model.CollectionEntity;
import app.community.persistence.collection.repository.CollectionRepository;

@Service
public class CollectionService {

	@Autowired
	private CollectionRepository collectionRepository;

	public CollectionListModel getCollections(Long communityId) {
		List<CollectionEntity> entityList = collectionRepository.findAllByCommunityId(communityId);
		CollectionListModel collectionListModel = new CollectionListModel();
		if (entityList.isEmpty()) {
			collectionListModel.setCollectionList(List.of());
		} else {
			CollectionMapper mapper = CollectionMapper.INSTANCE;
			List<CollectionModel> collectionList = mapper.toModel(entityList);
			collectionListModel.setCollectionList(collectionList);
		}
		return collectionListModel;
	}
}
