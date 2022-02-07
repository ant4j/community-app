package app.community.self.service.mapper;

import app.community.self.controller.model.CommunityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.self.persistence.model.CommunityEntity;

@Mapper
public interface CommunityMapper {
	public static final CommunityMapper INSTANCE = Mappers.getMapper(CommunityMapper.class);
	public CommunityEntity toEntity(CommunityDTO communityDTO);
	public CommunityDTO toDTO(CommunityEntity communityEntity);
}
