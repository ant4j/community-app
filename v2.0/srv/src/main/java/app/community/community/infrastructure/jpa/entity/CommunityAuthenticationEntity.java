package app.community.community.infrastructure.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "COMMUNITY_AUTHENTICATION")
public class CommunityAuthenticationEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	private Long communityId;
    
    @Column
	private String watchword;
}
