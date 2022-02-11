package app.community.presentation.content.controller;

import app.community.business.content.service.ContentService;
import app.community.domain.content.model.*;

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
	public @ResponseBody ResponseEntity<ContentListModel> getContents(@PathVariable Long collectionId) {
		LOG.info("ContentController, getContents, /contents/{}", collectionId);
		ContentListModel contentListModel = contentService.getContents(collectionId);
		return new ResponseEntity<ContentListModel>(contentListModel, HttpStatus.OK);
	}

	@GetMapping("/content/text/{contentId}")
	public @ResponseBody ResponseEntity<ContentTextModel> getContentText(@PathVariable Long contentId) {
		LOG.info("ContentController, getContentText, /content/text/{}", contentId);
		ContentTextModel contentTextModel = contentService.getContentText(contentId);
		return new ResponseEntity<ContentTextModel>(contentTextModel, HttpStatus.OK);
	}

	@PostMapping("/content/proposal")
	public @ResponseBody ResponseEntity<Void> proposeContent(@RequestBody ProposalParamModel proposalParamModel) {
		LOG.info("ContentController, proposeContent, /content/proposal");
		contentService.proposeContent(proposalParamModel);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/content/proposal/last/{communityId}")
	public @ResponseBody ResponseEntity<ProposalModel> getLastProposal(@PathVariable Long communityId) {
		LOG.info("ContentController, getLastProposal, /content/proposal/{}", communityId);
		ProposalModel proposalModel = contentService.getLastProposal(communityId);
		return new ResponseEntity<ProposalModel>(proposalModel, HttpStatus.OK);
	}
}
