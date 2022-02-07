package app.community.content.handler.mapper;

import app.community.content.controller.model.ContentDTO;
import app.community.content.persistence.model.ContentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContentMapper {
    public static final ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);
    public List<ContentDTO> toDTO(List<ContentEntity> entityList);
}