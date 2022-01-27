package app.community.self.controller.model;

import lombok.Data;

@Data
public class CommunityAuthenticationDTO {
	private Long communityId;
	private String watchword;
}
