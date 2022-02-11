package app.community.business.content.model;

import lombok.Data;

@Data
public class ContentTextDTO {
    private Long contentId;
    private String contentTitle;
    private String text;
}