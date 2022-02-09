package app.community.business.content.model;

import lombok.Data;

import java.util.List;

@Data
public class ContentListDTO {
    private String collectionName;
    private List<ContentDTO> contentList;
}
