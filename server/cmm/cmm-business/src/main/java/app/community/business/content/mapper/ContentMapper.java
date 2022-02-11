package app.community.business.content.mapper;

import app.community.domain.content.model.ContentModel;
import app.community.persistence.content.model.ContentEntity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContentMapper {
	
    public static final ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);
    
    public List<ContentModel> toModel(List<ContentEntity> entityList);

}
