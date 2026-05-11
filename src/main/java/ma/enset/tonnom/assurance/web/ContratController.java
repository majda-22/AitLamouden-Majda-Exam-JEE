package ma.enset.tonnom.assurance.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ma.enset.tonnom.assurance.dtos.ContratAssuranceDTO;
import ma.enset.tonnom.assurance.services.AssuranceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
@Tag(name = "Contrats", description = "Gestion des contrats d'assurance")
public class ContratController {

    private final AssuranceService service;

    @GetMapping
    @Operation(summary = "Lister tous les contrats")
    public List<ContratAssuranceDTO> getAllContrats() {
        return service.getAllContrats();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un contrat par son id")
    public ContratAssuranceDTO getContrat(@PathVariable Long id) {
        return service.getContrat(id);
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Lister les contrats d'un client")
    public List<ContratAssuranceDTO> getContratsByClient(@PathVariable Long clientId) {
        return service.getContratsByClient(clientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un nouveau contrat")
    public ContratAssuranceDTO createContrat(@RequestBody ContratAssuranceDTO dto) {
        return service.saveContrat(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un contrat")
    public void deleteContrat(@PathVariable Long id) {
        service.deleteContrat(id);
    }
}