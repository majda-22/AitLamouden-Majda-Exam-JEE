package ma.enset.tonnom.assurance.services;

import lombok.RequiredArgsConstructor;
import ma.enset.tonnom.assurance.dtos.*;
import ma.enset.tonnom.assurance.entities.*;
import ma.enset.tonnom.assurance.mappers.*;
import ma.enset.tonnom.assurance.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service                    
@Transactional              
@RequiredArgsConstructor    
public class AssuranceServiceImpl implements AssuranceService {

    private final ClientRepository clientRepository;
    private final ContratAssuranceRepository contratRepository;
    private final PaiementRepository paiementRepository;
    private final ClientMapper clientMapper;
    private final ContratMapper contratMapper;
    private final PaiementMapper paiementMapper;


    @Override
    public ClientDTO saveClient(ClientDTO dto) {
        Client client = clientMapper.toEntity(dto);
        Client saved = clientRepository.save(client);
        return clientMapper.toDTO(saved);
    }

    @Override
    public ClientDTO getClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable : " + id));
        return clientMapper.toDTO(client);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDTO)   // pour chaque client, on convertit en DTO
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }


    @Override
    public ContratAssuranceDTO saveContrat(ContratAssuranceDTO dto) {
        ContratAssurance contrat = contratMapper.toEntity(dto);
        ContratAssurance saved = contratRepository.save(contrat);
        return contratMapper.toDTO(saved);
    }

    @Override
    public ContratAssuranceDTO getContrat(Long id) {
        ContratAssurance contrat = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable : " + id));
        return contratMapper.toDTO(contrat);
    }

    @Override
    public List<ContratAssuranceDTO> getAllContrats() {
        return contratRepository.findAll()
                .stream()
                .map(contratMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContratAssuranceDTO> getContratsByClient(Long clientId) {
        return contratRepository.findByClientId(clientId)
                .stream()
                .map(contratMapper::toDTO)
                .collect(Collectors.toList());
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
    public List<PaiementDTO> getPaiementsByContrat(Long contratId) {
        return paiementRepository.findByContratId(contratId)
                .stream()
                .map(paiementMapper::toDTO)
                .collect(Collectors.toList());
    }
}