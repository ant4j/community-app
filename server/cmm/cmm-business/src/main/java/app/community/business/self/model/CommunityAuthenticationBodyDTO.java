package app.community.business.self.model;

import lombok.Data;

@Data
public class CommunityAuthenticationBodyDTO {
	private Long communityId;
	private String watchword;
}
