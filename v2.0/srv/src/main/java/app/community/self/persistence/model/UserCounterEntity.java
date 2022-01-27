package app.community.self.persistence.model;

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
@Table(name = "user_counter")
public class UserCounterEntity {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	private Long counter;
	
    @Version
    private Long version;
}
