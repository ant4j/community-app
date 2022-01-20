package app.community.community.domain.model;

import lombok.Data;

@Data
public class CommunityAuthenticationTO {
	private Long communityId;
	private String watchword;
}
