package ma.enset.tonnom.assurance.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)  // Chaque sous-classe a sa propre table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratAssurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateSouscription;

    @Enumerated(EnumType.STRING)  // Stocker le nom de l'enum, pas un chiffre
    private StatutContrat statut;  // EN_COURS, VALIDE, RESILIE

    private LocalDate dateValidation;
    private Double montantCotisation;
    private Integer dureeContrat;  // en mois
    private Double tauxCouverture;

    // Plusieurs contrats appartiennent à un seul client
    @ManyToOne
    @JoinColumn(name = "client_id")  // Nom de la colonne de clé étrangère
    private Client client;

    // Un contrat peut avoir plusieurs paiements
    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL)
    private List<Paiement> paiements;
}