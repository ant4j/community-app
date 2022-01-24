package app.community.self.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.community.self.controller.model.CommunityAuthenticationTO;
import app.community.self.controller.model.CommunityCodeTO;
import app.community.self.controller.model.CommunityTO;
import app.community.self.controller.model.UsernameTO;
import app.community.self.handler.CommunityHandler;

//TODO sistemare cross origin
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CommunityController {
	private Logger log = LoggerFactory.getLogger(CommunityController.class);

	@Autowired
	private CommunityHandler communityHandler;

	@GetMapping("/community/{code}")
	public @ResponseBody ResponseEntity<CommunityTO> getCommunity(CommunityCodeTO communityCodeTO) {
		log.info("CommunityController, getCommunity, /community/{}", communityCodeTO.getCode());
		CommunityTO communityTO = communityHandler.getCommunity(communityCodeTO);
		return new ResponseEntity<CommunityTO>(communityTO, HttpStatus.OK);
	}

	@PostMapping("/community/authentication")
	public @ResponseBody ResponseEntity<UsernameTO> authenticate(
			@RequestBody CommunityAuthenticationTO communityAuthenticationTO) {
		log.info("CommunityController, authenticate, /community/authentication");
		UsernameTO usernameTO = communityHandler.authenticate(communityAuthenticationTO);
		return new ResponseEntity<UsernameTO>(usernameTO, HttpStatus.OK);
	}

}
