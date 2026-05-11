package ma.enset.tonnom.assurance.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private Double montant;

    @Enumerated(EnumType.STRING)
    private TypePaiement type;  // MENSUALITE, PAIEMENT_ANNUEL, PAIEMENT_EXCEPTIONNEL

    @ManyToOne
    @JoinColumn(name = "contrat_id")
    private ContratAssurance contrat;
}