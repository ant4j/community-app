package app.community.persistence.self.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.community.persistence.self.model.CommunityEntity;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {
	
	public Optional<CommunityEntity> findByCode(String code);

}
