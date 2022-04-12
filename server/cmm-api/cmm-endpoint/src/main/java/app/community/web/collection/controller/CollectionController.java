package app.community.web.collection.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.community.business.collection.model.CollectionListModel;
import app.community.business.collection.service.CollectionService;

//TODO sistemare cross origin
@CrossOrigin
@RestController
@RequestMapping("/v1")
public class CollectionController {
	private static final Logger LOG = LoggerFactory.getLogger(CollectionController.class);

	@Autowired
	private CollectionService collectionService;

	@GetMapping("/communities/{id}/collections")
	public @ResponseBody ResponseEntity<CollectionListModel> getCollectionsByCommunityId(
			@PathVariable("id") Long communityId) {
		LOG.info("CollectionController, getCollectionsByCommunityId, /communities/{}/collections", communityId);
		CollectionListModel collectionListModel = collectionService.getCollectionsByCommunityId(communityId);
		return new ResponseEntity<CollectionListModel>(collectionListModel, HttpStatus.OK);
	}

}
