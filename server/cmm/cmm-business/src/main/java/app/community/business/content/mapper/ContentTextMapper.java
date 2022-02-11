package app.community.business.content.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.domain.content.model.ContentTextModel;
import app.community.persistence.content.model.ContentTextEntity;

@Mapper
public interface ContentTextMapper {
	
	public static final ContentTextMapper INSTANCE = Mappers.getMapper(ContentTextMapper.class);
	
	public ContentTextModel toModel(ContentTextEntity contentTextEntity);

}
