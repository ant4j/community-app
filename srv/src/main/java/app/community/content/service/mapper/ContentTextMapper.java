package app.community.content.service.mapper;

import app.community.content.controller.model.ContentTextDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.content.persistence.model.ContentTextEntity;

@Mapper
public interface ContentTextMapper {
	public static final ContentTextMapper INSTANCE = Mappers.getMapper(ContentTextMapper.class);
	public ContentTextDTO toDTO(ContentTextEntity contentTextEntity);
}
