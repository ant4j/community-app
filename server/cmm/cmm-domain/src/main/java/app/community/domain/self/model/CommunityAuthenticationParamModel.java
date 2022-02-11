package app.community.domain.self.model;

import lombok.Data;

@Data
public class CommunityAuthenticationParamModel {
	private Long communityId;
	private String watchword;
}
