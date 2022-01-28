package app.community.content.persistence.repository;

import app.community.content.persistence.model.ProposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProposalRepository extends JpaRepository<ProposalEntity, Long> {
    public Optional<ProposalEntity> findByCommunityId(Long communityId);
}
