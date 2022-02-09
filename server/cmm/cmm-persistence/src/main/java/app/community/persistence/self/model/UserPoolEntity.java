package app.community.persistence.self.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

@Data
@Entity
@Table(name = "user_pool")
public class UserPoolEntity {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	private Long userCodeId;
	
    @Version
    private Long version;
}
