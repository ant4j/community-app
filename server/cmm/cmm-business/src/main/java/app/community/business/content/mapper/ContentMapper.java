package app.community.business.content.mapper;

import app.community.business.content.model.ContentDTO;
import app.community.persistence.content.model.ContentEntity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContentMapper {
    public static final ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);
    public List<ContentDTO> toDTO(List<ContentEntity> entityList);
}
