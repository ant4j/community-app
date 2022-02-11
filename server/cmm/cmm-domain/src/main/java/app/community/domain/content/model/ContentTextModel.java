package app.community.domain.content.model;

import lombok.Data;

@Data
public class ContentTextModel {
    private Long contentId;
    private String contentTitle;
    private String text;
}
