package app.community.content.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

@Data
@Entity
@Table(name = "content_text")
public class ContentTextEntity {
	
	@Id
	@Column
	private Long contentId;
		
	@Lob
	@Column
	private String text;
	
	@Version
	private Long version;
}
