package app.community.web.self.controller;

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

import app.community.business.self.model.CommunityAuthParamModel;
import app.community.business.self.model.CommunityModel;
import app.community.business.self.model.UserModel;
import app.community.business.self.service.CommunityService;
import app.community.business.self.service.UserService;

//TODO sistemare cross origin, leggendo da file di properties tramite @Value la property corretta nel rispettivo profilo develop, test o prod
@CrossOrigin
@RestController
@RequestMapping("/v1")
public class CommunityController {
	private static final Logger LOG = LoggerFactory.getLogger(CommunityController.class);

	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/communities/{code}")
	public @ResponseBody ResponseEntity<CommunityModel> getCommunityByCode(@PathVariable("code") String code) {
		LOG.info("CommunityController, getCommunityByCode, /communities/{}", code);
		CommunityModel communityModel = communityService.getCommunityByCode(code);
		return new ResponseEntity<CommunityModel>(communityModel, HttpStatus.OK);
	}

	@PostMapping("/communities/{id}/signin")
	public @ResponseBody ResponseEntity<UserModel> signin(@PathVariable("id") Long communityId,
			@RequestBody CommunityAuthParamModel communityAuthParamModel) {
		LOG.info("CommunityController, signin, /communities/{}/signin", communityId);
		communityService.checkAuthentication(communityId, communityAuthParamModel);
		UserModel userModel = userService.detach();
		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
	}

}
