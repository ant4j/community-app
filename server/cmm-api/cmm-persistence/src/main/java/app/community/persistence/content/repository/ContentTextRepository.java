package app.community.persistence.content.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.community.persistence.content.model.ContentTextEntity;

@Repository
public interface ContentTextRepository extends JpaRepository<ContentTextEntity, Long> {

}
