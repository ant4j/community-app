package app.community.content.controller.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProposalDTO {
    private Long id;
    private Long communityId;
	private Long collectionId;
    private Integer collectionType;
    private Long contentId;
    private String contentTitle;
    private String username;
    private Date timestamp;
    private Boolean isPresent = false;
}
