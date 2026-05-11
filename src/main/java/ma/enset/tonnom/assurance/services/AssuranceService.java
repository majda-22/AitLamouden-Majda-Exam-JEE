package ma.enset.tonnom.assurance.services;

import ma.enset.tonnom.assurance.dtos.*;
import java.util.List;


public interface AssuranceService {

    
    ClientDTO saveClient(ClientDTO dto);
    ClientDTO getClient(Long id);
    List<ClientDTO> getAllClients();
    void deleteClient(Long id);

   
    ContratAssuranceDTO saveContrat(ContratAssuranceDTO dto);
    ContratAssuranceDTO getContrat(Long id);
    List<ContratAssuranceDTO> getAllContrats();
    List<ContratAssuranceDTO> getContratsByClient(Long clientId);
    void deleteContrat(Long id);

    
    PaiementDTO savePaiement(PaiementDTO dto);
    List<PaiementDTO> getPaiementsByContrat(Long contratId);
}