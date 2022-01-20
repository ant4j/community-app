package app.community.community.infrastructure.adapter.controller;

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

import app.community.community.application.port.CommunityUseCasePort;
import app.community.community.domain.model.CommunityTO;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/cmm-srv")
public class CommunityController {
	private Logger log = LoggerFactory.getLogger(CommunityController.class);
	
	@Autowired
	private CommunityUseCasePort communityUseCasePort;

	@GetMapping("/community/{communityCode}")
	public @ResponseBody ResponseEntity<CommunityTO> getCommunity(@PathVariable String communityCode) {
		log.info("Logging from /community/" + communityCode);
		CommunityTO communityTO = communityUseCasePort.getCommunity();
		return new ResponseEntity<CommunityTO>(communityTO, HttpStatus.OK);
	}

	@PostMapping("/community/auth")
	public @ResponseBody ResponseEntity<String> authenticate(@RequestBody String json) {
		log.info("Logging from /community/auth");
		log.info(">>>>>>>>>>>" + json.toString());
		communityUseCasePort.authenticate();
		return new ResponseEntity<String>("{\"username\":\"test\"}", HttpStatus.OK);
	}

}
