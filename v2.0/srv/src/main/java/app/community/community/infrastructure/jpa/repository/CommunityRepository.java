package app.community.community.infrastructure.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.community.community.infrastructure.jpa.entity.CommunityEntity;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {
	public Optional<CommunityEntity> findByCode(String code);
}
