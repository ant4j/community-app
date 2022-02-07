package app.community.self.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.community.self.persistence.model.UserPoolEntity;

@Repository
public interface UserPoolRepository extends JpaRepository<UserPoolEntity, Long> {

	default public Long detachUserCodeId() {
		Long userCodeId = save(new UserPoolEntity()).getUserCodeId();
		return userCodeId;
	}

}
