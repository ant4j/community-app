package app.community.restapi.self.controller;

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

import app.community.business.self.model.CommunityAuthenticationParamDTO;
import app.community.business.self.model.CommunityDTO;
import app.community.business.self.model.UsernameDTO;
import app.community.business.self.service.CommunityService;

//TODO sistemare cross origin, leggendo da file di properties tramite @Value la property corretta nel rispettivo profilo develop, test o prod
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CommunityController {
	private static final Logger LOG = LoggerFactory.getLogger(CommunityController.class);

	@Autowired
	private CommunityService communityService;

	@GetMapping("/community/{code}")
	public @ResponseBody ResponseEntity<CommunityDTO> getCommunity(@PathVariable String code) {
		LOG.info("CommunityController, getCommunity, /community/{}", code);
		CommunityDTO communityDTO = communityService.getCommunity(code);
		return new ResponseEntity<CommunityDTO>(communityDTO, HttpStatus.OK);
	}

	@PostMapping("/community/authentication")
	public @ResponseBody ResponseEntity<UsernameDTO> authenticate(
			@RequestBody CommunityAuthenticationParamDTO communityAuthenticationParamDTO) {
		LOG.info("CommunityController, authenticate, /community/authentication");
		UsernameDTO usernameDTO = communityService.authenticate(communityAuthenticationParamDTO);
		return new ResponseEntity<UsernameDTO>(usernameDTO, HttpStatus.OK);
	}

}
