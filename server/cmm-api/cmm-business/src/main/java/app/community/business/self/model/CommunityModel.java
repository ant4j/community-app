package app.community.business.self.model;

import lombok.Data;

@Data
public class CommunityModel {
	private Long id;
	private Long adminId;
	private String code;
	private String name;
}
