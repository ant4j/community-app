package app.community.self.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.self.persistence.model.CommunityEntity;
import app.community.self.service.model.CommunityDTO;

@Mapper
public interface CommunityMapper {
	public static final CommunityMapper INSTANCE = Mappers.getMapper(CommunityMapper.class);
	public CommunityEntity toEntity(CommunityDTO communityDTO);
	public CommunityDTO toDTO(CommunityEntity communityEntity);
}
