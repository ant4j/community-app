package app.community.business.self.model;

import lombok.Data;

@Data
public class CommunityAuthenticationParamDTO {
	private Long communityId;
	private String watchword;
}
