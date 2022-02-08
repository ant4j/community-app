package app.community.collection.service.model;

import lombok.Data;

@Data
public class CollectionDTO {
	private Long id;
	private Long communityId;
	private String name;
	private Integer type;
}
