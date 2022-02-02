package app.community.content.controller.model;

import lombok.Data;

@Data
public class ProposalBodyDTO {
    private Long communityId;
    private Long contentId;
    private String username;
}
