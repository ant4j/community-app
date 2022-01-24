package app.community.community.infrastructure.jpa.adapter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.community.domain.model.CommunityTO;
import app.community.community.infrastructure.jpa.entity.CommunityEntity;

@Mapper
public interface CommunityMapper {
	public static final CommunityMapper INSTANCE = Mappers.getMapper(CommunityMapper.class);
	public CommunityEntity toEntity(CommunityTO communityTo);
	public CommunityTO toModel(CommunityEntity communityEntity);
}
