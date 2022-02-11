package app.community.domain.content.model;

import lombok.Data;

import java.util.List;

@Data
public class ContentListModel {
    private String collectionName;
    private List<ContentModel> contentList;
}
