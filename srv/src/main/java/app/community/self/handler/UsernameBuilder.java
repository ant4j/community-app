package app.community.self.handler;

import org.springframework.stereotype.Component;

import app.community.self.controller.model.UsernameDTO;

@Component
public class UsernameBuilder {
	
	private static final String USERNAME_ROOT = "user";
	
	public UsernameDTO build(String code) {
        UsernameDTO usernameDTO = new UsernameDTO();
        usernameDTO.setUsername(USERNAME_ROOT.concat(code));
        return usernameDTO;
	}

}
