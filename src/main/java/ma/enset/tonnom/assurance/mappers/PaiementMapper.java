package ma.enset.tonnom.assurance.mappers;

import ma.enset.tonnom.assurance.dtos.PaiementDTO;
import ma.enset.tonnom.assurance.entities.Paiement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaiementMapper {

    @Mapping(source = "contrat.id", target = "contratId")
    PaiementDTO toDTO(Paiement paiement);

    @Mapping(source = "contratId", target = "contrat.id")
    Paiement toEntity(PaiementDTO dto);
}