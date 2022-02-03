package app.community.content.controller;

import app.community.content.controller.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.community.content.handler.ContentHandler;

//TODO sistemare cross origin
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ContentController {
    private static final Logger LOG = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private ContentHandler contentHandler;

    @GetMapping("/contents/{collectionId}")
    public @ResponseBody
    ResponseEntity<ContentListDTO> getContents(ContentParamDTO contentParamDTO) {
    	LOG.info("ContentController, getContents, /contents/{}", contentParamDTO.getCollectionId());
        ContentListDTO contentListDTO = contentHandler.getContents(contentParamDTO);
        return new ResponseEntity<ContentListDTO>(contentListDTO, HttpStatus.OK);
    }

    @GetMapping("/content/text/{contentId}")
    public @ResponseBody
    ResponseEntity<ContentTextDTO> getContentText(ContentTextParamDTO contentTextParamDTO) {
        LOG.info("ContentController, getContentText, /content/text/{}", contentTextParamDTO.getContentId());
        ContentTextDTO contentTextDTO = contentHandler.getContentText(contentTextParamDTO);
        return new ResponseEntity<ContentTextDTO>(contentTextDTO, HttpStatus.OK);
    }

    @PutMapping("/content/proposal")
    public @ResponseBody
    ResponseEntity<Void> proposeContent(@RequestBody ProposalBodyDTO proposalBodyDTO) {
        LOG.info("ContentController, proposeContent, /content/proposal");
        contentHandler.proposeContent(proposalBodyDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/content/proposal/{communityId}")
    public @ResponseBody
    ResponseEntity<ProposalDTO> getProposal(ProposalParamDTO proposalParamDTO) {
        LOG.info("ContentController, getProposal, /content/proposal/{}", proposalParamDTO.getCommunityId());
        ProposalDTO proposalDTO = contentHandler.getProposal(proposalParamDTO);
        return new ResponseEntity<ProposalDTO>(proposalDTO, HttpStatus.OK);
    }
}
