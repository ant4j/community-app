package app.community.self.controller;

import app.community.self.controller.model.CommunityAuthenticationParamDTO;
import app.community.self.controller.model.CommunityDTO;
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

import app.community.self.controller.model.CommunityParamDTO;
import app.community.self.controller.model.UsernameDTO;
import app.community.self.handler.CommunityHandler;

//TODO sistemare cross origin, leggendo da file di properties tramite @Value la property corretta nel rispettivo profilo develop, test o prod
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CommunityController {
	private static final Logger LOG = LoggerFactory.getLogger(CommunityController.class);

	@Autowired
	private CommunityHandler communityHandler;

	@GetMapping("/community/{code}")
	public @ResponseBody ResponseEntity<CommunityDTO> getCommunity(CommunityParamDTO communityParamDTO) {
		LOG.info("CommunityController, getCommunity, /community/{}", communityParamDTO.getCode());
		CommunityDTO communityDTO = communityHandler.getCommunity(communityParamDTO);
		return new ResponseEntity<CommunityDTO>(communityDTO, HttpStatus.OK);
	}

	@PostMapping("/community/authentication")
	public @ResponseBody ResponseEntity<UsernameDTO> authenticate(
			@RequestBody CommunityAuthenticationParamDTO communityAuthenticationParamDTO) {
		LOG.info("CommunityController, authenticate, /community/authentication");
		UsernameDTO usernameDTO = communityHandler.authenticate(communityAuthenticationParamDTO);
		return new ResponseEntity<UsernameDTO>(usernameDTO, HttpStatus.OK);
	}

}