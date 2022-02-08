package app.community.self.service.model;

import lombok.Data;

@Data
public class CommunityAuthenticationBodyDTO {
	private Long communityId;
	private String watchword;
}
