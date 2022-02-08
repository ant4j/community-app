package app.community.self.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.community.self.persistence.model.CommunityAuthenticationEntity;

@Repository
public interface CommunityAuthenticationRepository extends JpaRepository<CommunityAuthenticationEntity, Long> {

}
