package app.community.self.controller.model;

import lombok.Data;

@Data
public class CommunityAuthenticationBodyDTO {
	private Long communityId;
	private String watchword;
}
