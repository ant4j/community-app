package app.community.content.controller.model;

import lombok.Data;

@Data
public class ContentTextDTO {
    private Long contentId;
    private String contentTitle;
    private String text;
}
