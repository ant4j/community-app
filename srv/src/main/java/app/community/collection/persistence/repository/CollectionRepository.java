package app.community.collection.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.community.collection.persistence.model.CollectionEntity;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {

	public List<CollectionEntity> findAllByCommunityId(Long communityId);
}
