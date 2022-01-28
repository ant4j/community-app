package app.community.self.controller.model;

import lombok.Data;

@Data
public class CommunityAuthenticationParamDTO {
	private Long communityId;
	private String watchword;
}
