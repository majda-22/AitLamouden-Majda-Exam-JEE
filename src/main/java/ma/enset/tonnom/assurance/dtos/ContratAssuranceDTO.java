package ma.enset.tonnom.assurance.dtos;

import lombok.*;
import ma.enset.tonnom.assurance.entities.StatutContrat;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratAssuranceDTO {
    private Long id;
    private String typeContrat;
    private LocalDate dateSouscription;
    private StatutContrat statut;
    private LocalDate dateValidation;
    private Double montantCotisation;
    private Integer dureeContrat;
    private Double tauxCouverture;
    private Long clientId;       
    private String clientNom;
}
