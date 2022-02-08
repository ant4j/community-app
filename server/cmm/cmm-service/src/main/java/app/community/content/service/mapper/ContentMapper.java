package app.community.content.service.mapper;

import app.community.content.persistence.model.ContentEntity;
import app.community.content.service.model.ContentDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContentMapper {
    public static final ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);
    public List<ContentDTO> toDTO(List<ContentEntity> entityList);
}
