package app.community.self.handler.mapper;

import app.community.self.controller.model.CommunityAuthenticationParamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.self.persistence.model.CommunityAuthenticationEntity;

@Mapper
public interface CommunityAuthenticationMapper {
	public static final CommunityAuthenticationMapper INSTANCE = Mappers.getMapper(CommunityAuthenticationMapper.class);
	public CommunityAuthenticationEntity toEntity(CommunityAuthenticationParamDTO communityAuthenticationDTO);
	public CommunityAuthenticationParamDTO toDTO(CommunityAuthenticationEntity communityAuthenticationEntity);
}
