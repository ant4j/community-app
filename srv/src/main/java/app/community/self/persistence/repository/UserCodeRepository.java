package app.community.self.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.community.self.persistence.model.UserCodeEntity;

@Repository
public interface UserCodeRepository extends JpaRepository<UserCodeEntity, Long> {

}
