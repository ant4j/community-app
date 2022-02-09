package app.community.content.persistence.repository;

import app.community.content.persistence.model.ProposalEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProposalRepository extends JpaRepository<ProposalEntity, Long> {
	
	@Query(value = "SELECT p1 FROM ProposalEntity p1 WHERE p1.communityId = :communityId AND p1.id = (SELECT MAX(p2.id) FROM ProposalEntity p2 WHERE p2.communityId = :communityId)")
	public Optional<ProposalEntity> findLastProposal(@Param("communityId") Long communityId);

}
