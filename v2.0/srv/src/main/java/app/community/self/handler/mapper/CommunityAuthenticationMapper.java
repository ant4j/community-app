package app.community.self.handler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.self.controller.model.CommunityAuthenticationTO;
import app.community.self.persistence.model.CommunityAuthenticationEntity;

@Mapper
public interface CommunityAuthenticationMapper {
	public static final CommunityAuthenticationMapper INSTANCE = Mappers.getMapper(CommunityAuthenticationMapper.class);
	public CommunityAuthenticationEntity toEntity(CommunityAuthenticationTO communityAuthenticationTO);
	public CommunityAuthenticationTO toTO(CommunityAuthenticationEntity communityAuthenticationEntity);
}
