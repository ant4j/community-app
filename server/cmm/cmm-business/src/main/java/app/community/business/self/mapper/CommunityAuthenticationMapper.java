package app.community.business.self.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import app.community.business.self.model.CommunityAuthenticationBodyDTO;
import app.community.persistence.self.model.CommunityAuthenticationEntity;

@Mapper
public interface CommunityAuthenticationMapper {
	
	public static final CommunityAuthenticationMapper INSTANCE = Mappers.getMapper(CommunityAuthenticationMapper.class);
	
	public CommunityAuthenticationEntity toEntity(CommunityAuthenticationBodyDTO communityAuthenticationDTO);
	
	public CommunityAuthenticationBodyDTO toDTO(CommunityAuthenticationEntity communityAuthenticationEntity);

}
