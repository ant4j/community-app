package app.community.business.content.mapper;

import app.community.business.content.model.ProposalBodyDTO;
import app.community.business.content.model.ProposalDTO;
import app.community.persistence.content.model.ProposalEntity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProposalMapper {
    public static final ProposalMapper INSTANCE = Mappers.getMapper(ProposalMapper.class);
    public ProposalEntity toEntity(ProposalDTO proposalDTO);
    public ProposalDTO toDTO(ProposalEntity proposalEntity);
    public ProposalEntity toEntity(ProposalBodyDTO proposalParamDTO);
}
