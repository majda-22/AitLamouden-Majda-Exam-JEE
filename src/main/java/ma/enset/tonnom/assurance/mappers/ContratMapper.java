package ma.enset.tonnom.assurance.mappers;

import ma.enset.tonnom.assurance.dtos.ContratAssuranceDTO;
import ma.enset.tonnom.assurance.entities.ContratAssurance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContratMapper {

    @Mapping(source = "client.id",  target = "clientId")
    @Mapping(source = "client.nom", target = "clientNom")
    ContratAssuranceDTO toDTO(ContratAssurance contrat);

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(target = "paiements", ignore = true)
    ContratAssurance toEntity(ContratAssuranceDTO dto);
}
