package app.community.community.domain.model;

import lombok.Data;

@Data
public class CommunityTO {
	private Long id;
	private Long adminId;
	private String code;
	private String name;
}
