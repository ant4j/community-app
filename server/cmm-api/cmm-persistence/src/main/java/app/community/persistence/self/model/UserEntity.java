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
//TODO cambiare nome tabella in user
@Table(name = "user_code")
public class UserEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	private Long id;
	
	@Column
	private String code;
	
    @Version
    private Long version; 
	
}
