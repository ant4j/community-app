package app.community.business.content.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.business.content.model.ContentTextDTO;
import app.community.persistence.content.model.ContentTextEntity;

@Mapper
public interface ContentTextMapper {
	
	public static final ContentTextMapper INSTANCE = Mappers.getMapper(ContentTextMapper.class);
	
	public ContentTextDTO toDTO(ContentTextEntity contentTextEntity);

}
