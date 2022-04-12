package app.community.web.content.controller;

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
@RequestMapping("/v1")
public class ContentController {
	private static final Logger LOG = LoggerFactory.getLogger(ContentController.class);

	@Autowired
	private ContentService contentService;

	@GetMapping("/collections/{id}/contents")
	public @ResponseBody ResponseEntity<ContentListModel> getContentsByCollectionId(
			@PathVariable("id") Long collectionId) {
		LOG.info("ContentController, getContentsByCollectionId, /contents/{}", collectionId);
		ContentListModel contentListModel = contentService.getContentsByCollectionId(collectionId);
		return new ResponseEntity<ContentListModel>(contentListModel, HttpStatus.OK);
	}

	@GetMapping("/contents/{id}/content-text")
	public @ResponseBody ResponseEntity<ContentTextModel> getContentTextByContentId(
			@PathVariable("id") Long contentId) {
		LOG.info("ContentController, getContentTextByContentId, contents/{id}/content-text", contentId);
		ContentTextModel contentTextModel = contentService.getContentText(contentId);
		return new ResponseEntity<ContentTextModel>(contentTextModel, HttpStatus.OK);
	}

	@PostMapping("/proposals")
	public @ResponseBody ResponseEntity<Void> proposeContent(@RequestBody ProposalParamModel proposalParamModel) {
		LOG.info("ContentController, proposeContent, /proposals");
		contentService.proposeContent(proposalParamModel);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/communities/{id}/last-proposal")
	public @ResponseBody ResponseEntity<LastProposalModel> getLastProposalByCommunityId(
			@PathVariable("id") Long communityId) {
		LOG.info("ContentController, getLastProposalByCommunityId, /communities/{}/last-proposal", communityId);
		LastProposalModel lastProposalModel = contentService.getLastProposal(communityId);
		return new ResponseEntity<LastProposalModel>(lastProposalModel, HttpStatus.OK);
	}

}
