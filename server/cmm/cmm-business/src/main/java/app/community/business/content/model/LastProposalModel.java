package app.community.business.content.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LastProposalModel {
    private Long id;
    private Long communityId;
	private Long collectionId;
    private Integer collectionType;
    private Long contentId;
    private String contentTitle;
    private String username;
    private LocalDateTime proposedOn;
    private Boolean isPresent = false;
}
