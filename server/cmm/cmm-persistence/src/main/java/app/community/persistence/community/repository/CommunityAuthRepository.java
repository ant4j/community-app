package app.community.persistence.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.community.persistence.community.model.CommunityAuthEntity;

@Repository
public interface CommunityAuthRepository extends JpaRepository<CommunityAuthEntity, Long> {

}
