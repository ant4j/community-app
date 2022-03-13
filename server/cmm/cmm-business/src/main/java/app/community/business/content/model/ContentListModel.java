package app.community.business.content.model;

import lombok.Data;

import java.util.List;

@Data
public class ContentListModel {
    private String collectionName;
    private List<ContentModel> contentList;
}
