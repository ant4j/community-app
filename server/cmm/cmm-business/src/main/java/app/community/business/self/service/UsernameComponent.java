package app.community.business.self.service;

import org.springframework.stereotype.Component;

import app.community.domain.self.model.UsernameModel;

@Component
public class UsernameComponent {
	
	private static final String USERNAME_ROOT = "user";
	
	public UsernameModel buildUsername(String code) {
        UsernameModel usernameModel = new UsernameModel();
        usernameModel.setUsername(USERNAME_ROOT.concat(code));
        return usernameModel;
	}

}
