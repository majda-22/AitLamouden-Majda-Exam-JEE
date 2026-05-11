package ma.enset.tonnom.assurance.services;

import ma.enset.tonnom.assurance.dtos.*;
import java.util.List;


public interface AssuranceService {

    
    ClientDTO saveClient(ClientDTO dto);
    ClientDTO getClient(Long id);
    List<ClientDTO> getAllClients();
    ClientDTO updateClient(Long id, ClientDTO dto);
    void deleteClient(Long id);

   
    ContratAssuranceDTO saveContrat(ContratAssuranceDTO dto);
    ContratAutomobileDTO saveContratAutomobile(ContratAutomobileDTO dto);
    ContratHabitationDTO saveContratHabitation(ContratHabitationDTO dto);
    ContratSanteDTO saveContratSante(ContratSanteDTO dto);
    ContratAssuranceDTO getContrat(Long id);
    List<ContratAssuranceDTO> getAllContrats();
    List<ContratAssuranceDTO> getContratsByClient(Long clientId);
    ContratAssuranceDTO updateContrat(Long id, ContratAssuranceDTO dto);
    ContratAutomobileDTO updateContratAutomobile(Long id, ContratAutomobileDTO dto);
    ContratHabitationDTO updateContratHabitation(Long id, ContratHabitationDTO dto);
    ContratSanteDTO updateContratSante(Long id, ContratSanteDTO dto);
    void deleteContrat(Long id);

    
    PaiementDTO savePaiement(PaiementDTO dto);
    PaiementDTO updatePaiement(Long id, PaiementDTO dto);
    List<PaiementDTO> getPaiementsByContrat(Long contratId);
}
