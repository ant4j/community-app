package app.community.business.content.mapper;

import app.community.domain.content.model.ProposalModel;
import app.community.domain.content.model.ProposalParamModel;
import app.community.persistence.content.model.ProposalEntity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProposalMapper {
	
    public static final ProposalMapper INSTANCE = Mappers.getMapper(ProposalMapper.class);
    
    public ProposalEntity toEntity(ProposalModel proposalModel);
    
    public ProposalModel toModel(ProposalEntity proposalEntity);
    
    public ProposalEntity toEntity(ProposalParamModel proposalParamModel);

}
