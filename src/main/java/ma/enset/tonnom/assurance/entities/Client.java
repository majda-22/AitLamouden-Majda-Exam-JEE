package ma.enset.tonnom.assurance.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity                    // Cette classe = une table en base de données
@Data                      // Lombok génère automatiquement les getters/setters
@NoArgsConstructor         // Lombok génère un constructeur vide
@AllArgsConstructor        // Lombok génère un constructeur avec tous les champs
@Builder                   // Lombok permet de construire un objet facilement
public class Client {

    @Id                    // Ce champ est la clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment
    private Long id;

    private String nom;
    private String email;

    // Un client peut avoir PLUSIEURS contrats
    // mappedBy = le nom du champ "client" dans la classe ContratAssurance
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContratAssurance> contrats;
}