package app.community.domain.collection.model;

import lombok.Data;

@Data
public class CollectionModel {
	private Long id;
	private Long communityId;
	private String name;
	private Integer type;
}
