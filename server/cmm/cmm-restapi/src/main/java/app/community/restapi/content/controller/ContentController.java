package app.community.restapi.content.controller;

import app.community.business.content.model.*;
import app.community.business.content.service.ContentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//TODO sistemare cross origin
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ContentController {
	private static final Logger LOG = LoggerFactory.getLogger(ContentController.class);

	@Autowired
	private ContentService contentService;

	@GetMapping("/contents/{collectionId}")
	public @ResponseBody ResponseEntity<ContentListDTO> getContents(@PathVariable Long collectionId) {
		LOG.info("ContentController, getContents, /contents/{}", collectionId);
		ContentListDTO contentListDTO = contentService.getContents(collectionId);
		return new ResponseEntity<ContentListDTO>(contentListDTO, HttpStatus.OK);
	}

	@GetMapping("/content/text/{contentId}")
	public @ResponseBody ResponseEntity<ContentTextDTO> getContentText(@PathVariable Long contentId) {
		LOG.info("ContentController, getContentText, /content/text/{}", contentId);
		ContentTextDTO contentTextDTO = contentService.getContentText(contentId);
		return new ResponseEntity<ContentTextDTO>(contentTextDTO, HttpStatus.OK);
	}

	@PostMapping("/content/proposal")
	public @ResponseBody ResponseEntity<Void> proposeContent(@RequestBody ProposalParamDTO proposalParamDTO) {
		LOG.info("ContentController, proposeContent, /content/proposal");
		contentService.proposeContent(proposalParamDTO);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/content/proposal/last/{communityId}")
	public @ResponseBody ResponseEntity<ProposalDTO> getLastProposal(@PathVariable Long communityId) {
		LOG.info("ContentController, getLastProposal, /content/proposal/{}", communityId);
		ProposalDTO proposalDTO = contentService.getLastProposal(communityId);
		return new ResponseEntity<ProposalDTO>(proposalDTO, HttpStatus.OK);
	}
}
