package ma.enset.tonnom.assurance.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContratSante extends ContratAssurance {

    @Enumerated(EnumType.STRING)
    private NiveauCouverture niveauCouverture;  // BASIQUE, INTERMEDIAIRE, PREMIUM

    private Integer nombrePersonnesCouvertes;
}