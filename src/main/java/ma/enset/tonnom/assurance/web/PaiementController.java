package ma.enset.tonnom.assurance.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ma.enset.tonnom.assurance.dtos.PaiementDTO;
import ma.enset.tonnom.assurance.services.AssuranceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
@Tag(name = "Paiements", description = "Gestion des paiements")
public class PaiementController {

    private final AssuranceService service;

    @GetMapping("/contrat/{contratId}")
    @Operation(summary = "Lister les paiements d'un contrat")
    public List<PaiementDTO> getPaiements(@PathVariable Long contratId) {
        return service.getPaiementsByContrat(contratId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Enregistrer un paiement")
    public PaiementDTO createPaiement(@RequestBody PaiementDTO dto) {
        return service.savePaiement(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un paiement")
    public PaiementDTO updatePaiement(@PathVariable Long id, @RequestBody PaiementDTO dto) {
        return service.updatePaiement(id, dto);
    }
}
