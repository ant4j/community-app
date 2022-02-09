package app.community.business.self.model;

import lombok.Data;

@Data
public class CommunityDTO {
	private Long id;
	private Long adminId;
	private String code;
	private String name;
}
