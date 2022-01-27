package app.community.collection.handler.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.collection.controller.model.CollectionDTO;
import app.community.collection.persistence.model.CollectionEntity;

@Mapper
public interface CollectionMapper {
	public static final CollectionMapper INSTANCE = Mappers.getMapper(CollectionMapper.class);
	public List<CollectionDTO> toDTO(List<CollectionEntity> entityList);
}
