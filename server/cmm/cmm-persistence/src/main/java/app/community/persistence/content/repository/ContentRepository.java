package app.community.persistence.content.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.community.persistence.content.model.ContentEntity;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<ContentEntity, Long> {

    public List<ContentEntity> findAllByCollectionId(Long collectionId);

    @Query(value = "SELECT c.title FROM ContentEntity c WHERE c.id = :id")
    public String getTitleById(@Param("id") Long id);

}
