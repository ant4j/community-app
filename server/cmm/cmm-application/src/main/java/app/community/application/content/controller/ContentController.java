package app.community.application.content.controller;

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
	public @ResponseBody ResponseEntity<ContentListDTO> getContents(ContentParamDTO contentParamDTO) {
		LOG.info("ContentController, getContents, /contents/{}", contentParamDTO.getCollectionId());
		ContentListDTO contentListDTO = contentService.getContents(contentParamDTO);
		return new ResponseEntity<ContentListDTO>(contentListDTO, HttpStatus.OK);
	}

	@GetMapping("/content/text/{contentId}")
	public @ResponseBody ResponseEntity<ContentTextDTO> getContentText(ContentTextParamDTO contentTextParamDTO) {
		LOG.info("ContentController, getContentText, /content/text/{}", contentTextParamDTO.getContentId());
		ContentTextDTO contentTextDTO = contentService.getContentText(contentTextParamDTO);
		return new ResponseEntity<ContentTextDTO>(contentTextDTO, HttpStatus.OK);
	}

	@PostMapping("/content/proposal")
	public @ResponseBody ResponseEntity<Void> proposeContent(@RequestBody ProposalBodyDTO proposalBodyDTO) {
		LOG.info("ContentController, proposeContent, /content/proposal");
		contentService.proposeContent(proposalBodyDTO);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/content/proposal/last/{communityId}")
	public @ResponseBody ResponseEntity<ProposalDTO> getLastProposal(ProposalParamDTO proposalParamDTO) {
		LOG.info("ContentController, getLastProposal, /content/proposal/{}", proposalParamDTO.getCommunityId());
		ProposalDTO proposalDTO = contentService.getLastProposal(proposalParamDTO);
		return new ResponseEntity<ProposalDTO>(proposalDTO, HttpStatus.OK);
	}
}