package app.community.community.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.community.community.application.port.CommunityServicePort;
import app.community.community.domain.model.CommunityAdminTO;
import app.community.community.domain.model.CommunityAuthenticationTO;
import app.community.community.domain.model.CommunityTO;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CommunityController {
	private Logger log = LoggerFactory.getLogger(CommunityController.class);
	
	@Autowired
	private CommunityServicePort communityService;

	@PostMapping("/community")
	public @ResponseBody ResponseEntity<CommunityTO> createCommunity(@RequestBody CommunityTO communityTO) {
		log.info("Logging from /community (createCommunity)");
		CommunityTO communityTOResult = communityService.createCommunity(communityTO);
		return new ResponseEntity<CommunityTO>(communityTOResult, HttpStatus.OK);
	}
	
	@GetMapping("/community/{communityCode}")
	public @ResponseBody ResponseEntity<CommunityTO> getCommunity(@PathVariable String communityCode) {
		log.info("Logging from /community/" + communityCode + " (getCommunity)");
		CommunityTO communityTO = communityService.getCommunity(communityCode);
		return new ResponseEntity<CommunityTO>(communityTO, HttpStatus.OK);
	}

	@PostMapping("/community/authentication")
	public @ResponseBody ResponseEntity<String> authenticate(@RequestBody CommunityAuthenticationTO communityAuthenticationTO) {
		log.info("Logging from /community/authentication (authenticate)");
		communityService.authenticate(communityAuthenticationTO);
		return new ResponseEntity<String>("{\"username\":\"test\"}", HttpStatus.OK);
	}
	
	@PostMapping("/community/authentication/admin")
	public @ResponseBody ResponseEntity<String> authenticateAsAdmin(@RequestBody CommunityAdminTO communityAdminTO) {
		log.info("Logging from /community/authentication/admin (authenticateForAdmin)");
		log.info(">>>>>>>>>>>" + communityAdminTO.toString());
		return new ResponseEntity<String>("{\"adminId\":1}", HttpStatus.OK);
	}
}
