package app.community.persistence.content.model;

import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "proposal")
@DynamicUpdate
public class ProposalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long communityId;

    @Column
    private Long contentId;

    @Column
    private String username;

	@Column(columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime proposedOn;

    @Version
    private Long version;
}
