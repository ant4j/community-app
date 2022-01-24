package app.community.self.handler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.self.controller.model.CommunityTO;
import app.community.self.persistence.model.CommunityEntity;

@Mapper
public interface CommunityMapper {
	public static final CommunityMapper INSTANCE = Mappers.getMapper(CommunityMapper.class);
	public CommunityEntity toEntity(CommunityTO communityTO);
	public CommunityTO toTO(CommunityEntity communityEntity);
}
