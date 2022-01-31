package app.community.content.persistence.model;

import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

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
    private Date proposedOn;

    @Version
    private Long version;
}
