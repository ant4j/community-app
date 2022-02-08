package app.community.content.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.community.content.persistence.model.ContentEntity;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<ContentEntity, Long> {

    public List<ContentEntity> findAllByCollectionId(Long collectionId);

    @Query(value = "SELECT c.title FROM content c WHERE c.id = :id", nativeQuery = true)
    public String getTitleById(@Param("id") Long id);

}
