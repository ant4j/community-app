package app.community.community.infrastructure.jpa.adapter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.community.domain.model.CommunityAuthenticationTO;
import app.community.community.infrastructure.jpa.entity.CommunityAuthenticationEntity;

@Mapper
public interface CommunityAuthenticationMapper {
	public static final CommunityAuthenticationMapper INSTANCE = Mappers.getMapper(CommunityAuthenticationMapper.class);
	public CommunityAuthenticationEntity toEntity(CommunityAuthenticationTO communityAuthenticationTO);
	public CommunityAuthenticationTO toModel(CommunityAuthenticationEntity communityAuthenticationEntity);
}
