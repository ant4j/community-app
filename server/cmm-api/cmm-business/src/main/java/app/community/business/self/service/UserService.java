package app.community.business.self.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.community.business.self.model.UserModel;
import app.community.persistence.self.model.UserEntity;
import app.community.persistence.self.model.UserPoolEntity;
import app.community.persistence.self.repository.UserRepository;
import app.community.persistence.self.repository.UserPoolRepository;

@Service
public class UserService {
	
	private static final String USERNAME_PREFIX = "user";
	
	@Autowired
	private UserPoolRepository userPoolRepository;

	@Autowired
	private UserRepository userRepository;
	
	public UserModel detach() {
		Long userCodeId = userPoolRepository.save(new UserPoolEntity()).getUserCodeId();
		// TODO controllo optional.isPresent(), altrimenti sono finiti i code
		UserEntity userEntity = userRepository.findById(userCodeId).get();
        UserModel userModel = new UserModel();
        userModel.setUsername(USERNAME_PREFIX.concat(userEntity.getCode()));
        return userModel;
	}

}
