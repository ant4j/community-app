package app.community.business.self.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.domain.self.model.CommunityModel;
import app.community.persistence.self.model.CommunityEntity;

@Mapper
public interface CommunityMapper {
	
	public static final CommunityMapper INSTANCE = Mappers.getMapper(CommunityMapper.class);

	public CommunityEntity toEntity(CommunityModel communityModel);

	public CommunityModel toModel(CommunityEntity communityEntity);

}
