package app.community.self.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.community.self.persistence.model.UserCounterEntity;

@Repository
public interface UserCounterRepository extends JpaRepository<UserCounterEntity, Long> {

	default public Long detachCounter() {
		final Long USER_COUNTER_ID = 1L;
		UserCounterEntity userCounterEntity = findById(USER_COUNTER_ID).get();
		Long next = userCounterEntity.getCounter() + 1L;
		userCounterEntity.setCounter(next);
		save(userCounterEntity);
		return next;
	}

}
