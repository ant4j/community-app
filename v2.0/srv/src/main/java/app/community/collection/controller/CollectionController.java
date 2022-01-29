package app.community.collection.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.community.collection.controller.model.CollectionListDTO;
import app.community.collection.controller.model.CommunityParamDTO;
import app.community.collection.handler.CollectionHandler;

//TODO sistemare cross origin
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CollectionController {
    private static final Logger LOG = LoggerFactory.getLogger(CollectionController.class);

    @Autowired
    private CollectionHandler collectionHandler;

    @GetMapping("/collections/{communityId}")
    public @ResponseBody
    ResponseEntity<CollectionListDTO> getCollections(CommunityParamDTO communityParamDTO) {
        LOG.info("CollectionController, getCollections, /collection/{}", communityParamDTO.getCommunityId());
        CollectionListDTO collectionListDTO = collectionHandler.getCollections(communityParamDTO);
        return new ResponseEntity<CollectionListDTO>(collectionListDTO, HttpStatus.OK);
    }

}