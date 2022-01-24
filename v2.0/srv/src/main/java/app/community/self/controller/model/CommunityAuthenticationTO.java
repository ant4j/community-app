package app.community.self.controller.model;

import lombok.Data;

@Data
public class CommunityAuthenticationTO {
	private Long communityId;
	private String watchword;
}
