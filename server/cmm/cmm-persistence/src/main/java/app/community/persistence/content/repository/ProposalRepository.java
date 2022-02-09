package app.community.persistence.content.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.community.persistence.content.model.ProposalEntity;

import java.util.Optional;

@Repository
public interface ProposalRepository extends JpaRepository<ProposalEntity, Long> {
	
	@Query(value = "SELECT p1 FROM ProposalEntity p1 WHERE p1.communityId = :communityId AND p1.id = (SELECT MAX(p2.id) FROM ProposalEntity p2 WHERE p2.communityId = :communityId)")
	public Optional<ProposalEntity> findLastProposal(@Param("communityId") Long communityId);

}
