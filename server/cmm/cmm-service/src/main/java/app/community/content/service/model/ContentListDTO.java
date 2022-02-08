package app.community.content.service.model;

import lombok.Data;

import java.util.List;

@Data
public class ContentListDTO {
    private String collectionName;
    private List<ContentDTO> contentList;
}
