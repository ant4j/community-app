package app.community.self.service.model;

import lombok.Data;

@Data
public class CommunityDTO {
	private Long id;
	private Long adminId;
	private String code;
	private String name;
}
