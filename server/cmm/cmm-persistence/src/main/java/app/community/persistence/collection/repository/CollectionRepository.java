package app.community.persistence.collection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.community.persistence.collection.model.CollectionEntity;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {

	public List<CollectionEntity> findAllByCommunityId(Long communityId);
	
    @Query(value = "SELECT c.name FROM CollectionEntity c WHERE c.id = :collectionId")
    public String getCollectionNameByCollectionId(@Param("collectionId") Long collectionId);

    @Query(value = "SELECT c.type FROM CollectionEntity c WHERE c.id = :collectionId")
    public Integer getCollectionTypeByCollectionId(@Param("collectionId") Long collectionId);
    
}
