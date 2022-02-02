package app.community.self.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.community.self.persistence.model.UserCounterEntity;

@Repository
public interface UserCounterRepository extends JpaRepository<UserCounterEntity, Long> {

	default public Long detachCounter() {
		Long next = save(new UserCounterEntity()).getCounter();
		return next;
	}

}
