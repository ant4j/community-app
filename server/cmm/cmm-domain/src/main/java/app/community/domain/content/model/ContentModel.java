package app.community.domain.content.model;

import lombok.Data;

@Data
public class ContentModel {
    private Long id;
    private Long collectionId;
    private String title;
}
