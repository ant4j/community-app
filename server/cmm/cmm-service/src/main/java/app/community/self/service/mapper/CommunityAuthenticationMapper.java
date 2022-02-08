package app.community.self.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.self.persistence.model.CommunityAuthenticationEntity;
import app.community.self.service.model.CommunityAuthenticationBodyDTO;

@Mapper
public interface CommunityAuthenticationMapper {
	public static final CommunityAuthenticationMapper INSTANCE = Mappers.getMapper(CommunityAuthenticationMapper.class);
	public CommunityAuthenticationEntity toEntity(CommunityAuthenticationBodyDTO communityAuthenticationDTO);
	public CommunityAuthenticationBodyDTO toDTO(CommunityAuthenticationEntity communityAuthenticationEntity);
}
