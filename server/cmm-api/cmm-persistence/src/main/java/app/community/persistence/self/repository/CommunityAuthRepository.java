package app.community.persistence.self.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.community.persistence.self.model.CommunityAuthEntity;

@Repository
public interface CommunityAuthRepository extends JpaRepository<CommunityAuthEntity, Long> {

}
