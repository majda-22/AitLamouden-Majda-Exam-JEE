package ma.enset.tonnom.assurance.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ma.enset.tonnom.assurance.dtos.ClientDTO;
import ma.enset.tonnom.assurance.services.AssuranceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                     
@RequestMapping("/api/clients")     
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Gestion des clients")  
public class ClientController {

    private final AssuranceService service;

    @GetMapping
    @Operation(summary = "Lister tous les clients")
    public List<ClientDTO> getAllClients() {
        return service.getAllClients();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un client par son id")
    public ClientDTO getClient(@PathVariable Long id) {
        return service.getClient(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)   // Renvoie 201 au lieu de 200
    @Operation(summary = "Créer un nouveau client")
    public ClientDTO createClient(@RequestBody ClientDTO dto) {
        return service.saveClient(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un client")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO dto) {
        return service.updateClient(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  
    @Operation(summary = "Supprimer un client")
    public void deleteClient(@PathVariable Long id) {
        service.deleteClient(id);
    }
}
