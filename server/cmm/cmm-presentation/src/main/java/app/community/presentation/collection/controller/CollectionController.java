package app.community.presentation.collection.controller;

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

import app.community.business.collection.service.CollectionService;
import app.community.domain.collection.model.CollectionListModel;

//TODO sistemare cross origin
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CollectionController {
    private static final Logger LOG = LoggerFactory.getLogger(CollectionController.class);

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/collections/{communityId}")
    public @ResponseBody
    ResponseEntity<CollectionListModel> getCollections(@PathVariable Long communityId) {
        LOG.info("CollectionController, getCollections, /collection/{}", communityId);
        CollectionListModel collectionListModel = collectionService.getCollections(communityId);
        return new ResponseEntity<CollectionListModel>(collectionListModel, HttpStatus.OK);
    }

}
