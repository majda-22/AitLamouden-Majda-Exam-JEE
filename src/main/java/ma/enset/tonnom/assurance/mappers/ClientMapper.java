package ma.enset.tonnom.assurance.mappers;

import ma.enset.tonnom.assurance.dtos.ClientDTO;
import ma.enset.tonnom.assurance.entities.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDTO(Client client);
    Client toEntity(ClientDTO dto);
}