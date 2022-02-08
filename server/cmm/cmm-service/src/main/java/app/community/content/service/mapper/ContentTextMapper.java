package app.community.content.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.content.persistence.model.ContentTextEntity;
import app.community.content.service.model.ContentTextDTO;

@Mapper
public interface ContentTextMapper {
	public static final ContentTextMapper INSTANCE = Mappers.getMapper(ContentTextMapper.class);
	public ContentTextDTO toDTO(ContentTextEntity contentTextEntity);
}
