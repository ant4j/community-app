package app.community.collection.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.community.collection.persistence.model.CollectionEntity;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {

	public List<CollectionEntity> findAllByCommunityId(Long communityId);
	
    @Query(value = "SELECT c.name FROM collection c WHERE c.id = :collectionId", nativeQuery = true)
    public String getCollectionNameByCollectionId(@Param("collectionId") Long collectionId);

    @Query(value = "SELECT c.type FROM collection c WHERE c.id = :collectionId", nativeQuery = true)
    public Integer getCollectionTypeByCollectionId(@Param("collectionId") Long collectionId);
    
}
