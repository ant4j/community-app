package app.community.domain.content.model;

import lombok.Data;

@Data
public class ProposalParamModel {
    private Long communityId;
    private Long contentId;
    private String username;
}
