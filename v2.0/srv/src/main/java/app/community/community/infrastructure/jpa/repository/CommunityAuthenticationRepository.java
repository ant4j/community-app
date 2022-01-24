package app.community.community.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.community.community.infrastructure.jpa.entity.CommunityAuthenticationEntity;

@Repository
public interface CommunityAuthenticationRepository extends JpaRepository<CommunityAuthenticationEntity, Long> {

}
