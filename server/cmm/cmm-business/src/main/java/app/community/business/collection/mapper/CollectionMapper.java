package app.community.business.collection.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.business.collection.model.CollectionDTO;
import app.community.persistence.collection.model.CollectionEntity;

@Mapper
public interface CollectionMapper {
	
	public static final CollectionMapper INSTANCE = Mappers.getMapper(CollectionMapper.class);
	
	public List<CollectionDTO> toDTO(List<CollectionEntity> entityList);

}
