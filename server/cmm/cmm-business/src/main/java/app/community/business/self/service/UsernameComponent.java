package app.community.business.self.service;

import org.springframework.stereotype.Component;

import app.community.business.self.model.UsernameDTO;

@Component
public class UsernameComponent {
	
	private static final String USERNAME_ROOT = "user";
	
	public UsernameDTO buildUsername(String code) {
        UsernameDTO usernameDTO = new UsernameDTO();
        usernameDTO.setUsername(USERNAME_ROOT.concat(code));
        return usernameDTO;
	}

}
