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
        //TODO migliorare log
        LOG.info("getContents");
        ContentListDTO contentListDTO = contentHandler.getContents(contentParamDTO);
        return new ResponseEntity<ContentListDTO>(contentListDTO, HttpStatus.OK);
    }

    @GetMapping("/content/text/{contentId}/{collectionId}")
    public @ResponseBody
    ResponseEntity<ContentTextDTO> getContentText(ContentTextParamDTO contentTextParamDTO) {
        //TODO migliorare log
        LOG.info("getContent");
        ContentTextDTO contentTextDTO = contentHandler.getContentText(contentTextParamDTO);
        return new ResponseEntity<ContentTextDTO>(contentTextDTO, HttpStatus.OK);
    }

    @PostMapping("/content/proposal")
    public @ResponseBody
    ResponseEntity<Void> proposeContent(@RequestBody ProposalParamDTO proposalParamDTO) {
        //TODO migliorare log
        LOG.info("proposeContent");
        contentHandler.proposeContent(proposalParamDTO);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    //TODO fare un ParamDTO dedicato
    @GetMapping("/content/proposal/{communityId}")
    public @ResponseBody
    ResponseEntity<ProposalDTO> getProposal(ProposalParamDTO proposalParamDTO) {
        //TODO migliorare log
        LOG.info("getProposal");
        ProposalDTO proposalDTO = contentHandler.getProposal(proposalParamDTO);
        return new ResponseEntity<ProposalDTO>(proposalDTO, HttpStatus.OK);
    }
}
