package app.community.content.service.mapper;

import app.community.content.controller.model.ProposalDTO;
import app.community.content.controller.model.ProposalBodyDTO;
import app.community.content.persistence.model.ProposalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProposalMapper {
    public static final ProposalMapper INSTANCE = Mappers.getMapper(ProposalMapper.class);
    public ProposalEntity toEntity(ProposalDTO proposalDTO);
    public ProposalDTO toDTO(ProposalEntity proposalEntity);
    public ProposalEntity toEntity(ProposalBodyDTO proposalParamDTO);
}
