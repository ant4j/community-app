package app.community.self.service.handler;

import org.springframework.stereotype.Component;

import app.community.self.controller.model.UsernameDTO;

@Component
public class UsernameHandler {
	
	private static final String USERNAME_ROOT = "user";
	
	public UsernameDTO buildUsername(String code) {
        UsernameDTO usernameDTO = new UsernameDTO();
        usernameDTO.setUsername(USERNAME_ROOT.concat(code));
        return usernameDTO;
	}

}
