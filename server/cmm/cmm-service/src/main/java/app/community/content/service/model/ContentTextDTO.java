package app.community.content.service.model;

import lombok.Data;

@Data
public class ContentTextDTO {
    private Long contentId;
    private String contentTitle;
    private String text;
}
