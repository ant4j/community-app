package app.community.content.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.community.content.persistence.model.ContentTextEntity;

@Repository
public interface ContentTextRepository extends JpaRepository<ContentTextEntity, Long> {

}
