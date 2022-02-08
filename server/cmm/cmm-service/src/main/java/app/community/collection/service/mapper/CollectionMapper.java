package app.community.collection.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.collection.persistence.model.CollectionEntity;
import app.community.collection.service.model.CollectionDTO;

@Mapper
public interface CollectionMapper {
	public static final CollectionMapper INSTANCE = Mappers.getMapper(CollectionMapper.class);
	public List<CollectionDTO> toDTO(List<CollectionEntity> entityList);
}
