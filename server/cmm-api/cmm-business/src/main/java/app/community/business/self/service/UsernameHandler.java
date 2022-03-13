package app.community.business.self.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.community.business.self.model.UsernameModel;
import app.community.persistence.community.model.UserCodeEntity;
import app.community.persistence.community.model.UserPoolEntity;
import app.community.persistence.community.repository.UserCodeRepository;
import app.community.persistence.community.repository.UserPoolRepository;

@Component
public class UsernameHandler {
	
	private static final String USERNAME_ROOT = "user";
	
	@Autowired
	private UserPoolRepository userPoolRepository;

	@Autowired
	private UserCodeRepository userCodeRepository;
	
	public UsernameModel detachUsername() {
		Long userCodeId = userPoolRepository.save(new UserPoolEntity()).getUserCodeId();
		// TODO controllo optional.isPresent(), altrimenti sono finiti i code
		UserCodeEntity userCodeEntity = userCodeRepository.findById(userCodeId).get();
        UsernameModel usernameModel = new UsernameModel();
        usernameModel.setUsername(USERNAME_ROOT.concat(userCodeEntity.getCode()));
        return usernameModel;
	}

}
