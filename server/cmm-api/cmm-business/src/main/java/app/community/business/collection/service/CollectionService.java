package app.community.business.collection.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.business.collection.model.CollectionModel;
import app.community.business.collection.model.CollectionListModel;
import app.community.persistence.collection.model.CollectionEntity;
import app.community.persistence.collection.repository.CollectionRepository;

@Service
public class CollectionService {

	@Autowired
	private CollectionRepository collectionRepository;

	@Autowired
	private ModelMapper mapper;
	
	public CollectionListModel getCollectionsByCommunityId(Long communityId) {
		List<CollectionEntity> entityList = collectionRepository.findAllByCommunityId(communityId);
		CollectionListModel collectionListModel = new CollectionListModel();
		List<CollectionModel> collectionList = entityList.stream()
				.map(collectionEntity -> mapper.map(collectionEntity, CollectionModel.class))
				.collect(Collectors.toList());
		collectionListModel.setCollectionList(collectionList);
		return collectionListModel;
	}
	
}
