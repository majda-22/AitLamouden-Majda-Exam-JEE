package ma.enset.tonnom.assurance.mappers;

import ma.enset.tonnom.assurance.dtos.ClientDTO;
import ma.enset.tonnom.assurance.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDTO(Client client);

    @Mapping(target = "contrats", ignore = true)
    Client toEntity(ClientDTO dto);
}
