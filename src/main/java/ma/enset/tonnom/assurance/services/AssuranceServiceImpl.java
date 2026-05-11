package ma.enset.tonnom.assurance.services;

import lombok.RequiredArgsConstructor;
import ma.enset.tonnom.assurance.dtos.*;
import ma.enset.tonnom.assurance.entities.*;
import ma.enset.tonnom.assurance.mappers.ClientMapper;
import ma.enset.tonnom.assurance.mappers.PaiementMapper;
import ma.enset.tonnom.assurance.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AssuranceServiceImpl implements AssuranceService {

    private final ClientRepository clientRepository;
    private final ContratAssuranceRepository contratRepository;
    private final PaiementRepository paiementRepository;
    private final ClientMapper clientMapper;
    private final PaiementMapper paiementMapper;

    @Override
    public ClientDTO saveClient(ClientDTO dto) {
        Client client = clientMapper.toEntity(dto);
        Client saved = clientRepository.save(client);
        return clientMapper.toDTO(saved);
    }

    @Override
    public ClientDTO getClient(Long id) {
        return clientMapper.toDTO(findClient(id));
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO dto) {
        Client client = findClient(id);
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ContratAssuranceDTO saveContrat(ContratAssuranceDTO dto) {
        ContratAssurance contrat = new ContratAssurance();
        applyCommonContratFields(contrat, dto);
        return toDTO(contratRepository.save(contrat));
    }

    @Override
    public ContratAutomobileDTO saveContratAutomobile(ContratAutomobileDTO dto) {
        ContratAutomobile contrat = new ContratAutomobile();
        applyCommonContratFields(contrat, dto);
        applyAutomobileFields(contrat, dto);
        return (ContratAutomobileDTO) toDTO(contratRepository.save(contrat));
    }

    @Override
    public ContratHabitationDTO saveContratHabitation(ContratHabitationDTO dto) {
        ContratHabitation contrat = new ContratHabitation();
        applyCommonContratFields(contrat, dto);
        applyHabitationFields(contrat, dto);
        return (ContratHabitationDTO) toDTO(contratRepository.save(contrat));
    }

    @Override
    public ContratSanteDTO saveContratSante(ContratSanteDTO dto) {
        ContratSante contrat = new ContratSante();
        applyCommonContratFields(contrat, dto);
        applySanteFields(contrat, dto);
        return (ContratSanteDTO) toDTO(contratRepository.save(contrat));
    }

    @Override
    public ContratAssuranceDTO getContrat(Long id) {
        return toDTO(findContrat(id));
    }

    @Override
    public List<ContratAssuranceDTO> getAllContrats() {
        return contratRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<ContratAssuranceDTO> getContratsByClient(Long clientId) {
        return contratRepository.findByClientId(clientId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public ContratAssuranceDTO updateContrat(Long id, ContratAssuranceDTO dto) {
        ContratAssurance contrat = findContrat(id);
        applyCommonContratFields(contrat, dto);
        return toDTO(contratRepository.save(contrat));
    }

    @Override
    public ContratAutomobileDTO updateContratAutomobile(Long id, ContratAutomobileDTO dto) {
        ContratAutomobile contrat = requireContratType(id, ContratAutomobile.class);
        applyCommonContratFields(contrat, dto);
        applyAutomobileFields(contrat, dto);
        return (ContratAutomobileDTO) toDTO(contratRepository.save(contrat));
    }

    @Override
    public ContratHabitationDTO updateContratHabitation(Long id, ContratHabitationDTO dto) {
        ContratHabitation contrat = requireContratType(id, ContratHabitation.class);
        applyCommonContratFields(contrat, dto);
        applyHabitationFields(contrat, dto);
        return (ContratHabitationDTO) toDTO(contratRepository.save(contrat));
    }

    @Override
    public ContratSanteDTO updateContratSante(Long id, ContratSanteDTO dto) {
        ContratSante contrat = requireContratType(id, ContratSante.class);
        applyCommonContratFields(contrat, dto);
        applySanteFields(contrat, dto);
        return (ContratSanteDTO) toDTO(contratRepository.save(contrat));
    }

    @Override
    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }

    @Override
    public PaiementDTO savePaiement(PaiementDTO dto) {
        Paiement paiement = paiementMapper.toEntity(dto);
        Paiement saved = paiementRepository.save(paiement);
        return paiementMapper.toDTO(saved);
    }

    @Override
    public PaiementDTO updatePaiement(Long id, PaiementDTO dto) {
        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable : " + id));
        paiement.setDate(dto.getDate());
        paiement.setMontant(dto.getMontant());
        paiement.setType(dto.getType());
        if (dto.getContratId() != null) {
            paiement.setContrat(findContrat(dto.getContratId()));
        }
        return paiementMapper.toDTO(paiementRepository.save(paiement));
    }

    @Override
    public List<PaiementDTO> getPaiementsByContrat(Long contratId) {
        return paiementRepository.findByContratId(contratId)
                .stream()
                .map(paiementMapper::toDTO)
                .toList();
    }

    private Client findClient(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable : " + id));
    }

    private ContratAssurance findContrat(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable : " + id));
    }

    private <T extends ContratAssurance> T requireContratType(Long id, Class<T> type) {
        ContratAssurance contrat = findContrat(id);
        if (!type.isInstance(contrat)) {
            throw new RuntimeException("Le contrat " + id + " n'est pas de type " + type.getSimpleName());
        }
        return type.cast(contrat);
    }

    private void applyCommonContratFields(ContratAssurance contrat, ContratAssuranceDTO dto) {
        contrat.setDateSouscription(dto.getDateSouscription());
        contrat.setStatut(dto.getStatut());
        contrat.setDateValidation(dto.getDateValidation());
        contrat.setMontantCotisation(dto.getMontantCotisation());
        contrat.setDureeContrat(dto.getDureeContrat());
        contrat.setTauxCouverture(dto.getTauxCouverture());
        if (dto.getClientId() != null) {
            contrat.setClient(findClient(dto.getClientId()));
        }
    }

    private void applyAutomobileFields(ContratAutomobile contrat, ContratAutomobileDTO dto) {
        contrat.setNumeroImmatriculation(dto.getNumeroImmatriculation());
        contrat.setMarqueVehicule(dto.getMarqueVehicule());
        contrat.setModeleVehicule(dto.getModeleVehicule());
    }

    private void applyHabitationFields(ContratHabitation contrat, ContratHabitationDTO dto) {
        contrat.setTypeLogement(dto.getTypeLogement());
        contrat.setAdresse(dto.getAdresse());
        contrat.setSuperficie(dto.getSuperficie());
    }

    private void applySanteFields(ContratSante contrat, ContratSanteDTO dto) {
        contrat.setNiveauCouverture(dto.getNiveauCouverture());
        contrat.setNombrePersonnesCouvertes(dto.getNombrePersonnesCouvertes());
    }

    private ContratAssuranceDTO toDTO(ContratAssurance contrat) {
        ContratAssuranceDTO dto;
        if (contrat instanceof ContratAutomobile automobile) {
            ContratAutomobileDTO automobileDTO = new ContratAutomobileDTO();
            automobileDTO.setNumeroImmatriculation(automobile.getNumeroImmatriculation());
            automobileDTO.setMarqueVehicule(automobile.getMarqueVehicule());
            automobileDTO.setModeleVehicule(automobile.getModeleVehicule());
            automobileDTO.setTypeContrat("AUTOMOBILE");
            dto = automobileDTO;
        } else if (contrat instanceof ContratHabitation habitation) {
            ContratHabitationDTO habitationDTO = new ContratHabitationDTO();
            habitationDTO.setTypeLogement(habitation.getTypeLogement());
            habitationDTO.setAdresse(habitation.getAdresse());
            habitationDTO.setSuperficie(habitation.getSuperficie());
            habitationDTO.setTypeContrat("HABITATION");
            dto = habitationDTO;
        } else if (contrat instanceof ContratSante sante) {
            ContratSanteDTO santeDTO = new ContratSanteDTO();
            santeDTO.setNiveauCouverture(sante.getNiveauCouverture());
            santeDTO.setNombrePersonnesCouvertes(sante.getNombrePersonnesCouvertes());
            santeDTO.setTypeContrat("SANTE");
            dto = santeDTO;
        } else {
            dto = new ContratAssuranceDTO();
            dto.setTypeContrat("GENERAL");
        }

        dto.setId(contrat.getId());
        dto.setDateSouscription(contrat.getDateSouscription());
        dto.setStatut(contrat.getStatut());
        dto.setDateValidation(contrat.getDateValidation());
        dto.setMontantCotisation(contrat.getMontantCotisation());
        dto.setDureeContrat(contrat.getDureeContrat());
        dto.setTauxCouverture(contrat.getTauxCouverture());
        if (contrat.getClient() != null) {
            dto.setClientId(contrat.getClient().getId());
            dto.setClientNom(contrat.getClient().getNom());
        }
        return dto;
    }
}
