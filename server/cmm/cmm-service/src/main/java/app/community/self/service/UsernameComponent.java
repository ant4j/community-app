package app.community.self.service;

import org.springframework.stereotype.Component;

import app.community.self.service.model.UsernameDTO;

@Component
public class UsernameComponent {
	
	private static final String USERNAME_ROOT = "user";
	
	public UsernameDTO buildUsername(String code) {
        UsernameDTO usernameDTO = new UsernameDTO();
        usernameDTO.setUsername(USERNAME_ROOT.concat(code));
        return usernameDTO;
	}

}
