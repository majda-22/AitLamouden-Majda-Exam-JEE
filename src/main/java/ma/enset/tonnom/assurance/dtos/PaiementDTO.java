package ma.enset.tonnom.assurance.dtos;

import lombok.*;
import ma.enset.tonnom.assurance.entities.TypePaiement;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaiementDTO {
    private Long id;
    private LocalDate date;
    private Double montant;
    private TypePaiement type;
    private Long contratId;
}