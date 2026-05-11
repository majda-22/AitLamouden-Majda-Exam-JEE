package ma.enset.tonnom.assurance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)  
public class ContratAutomobile extends ContratAssurance {

    private String numeroImmatriculation;
    private String marqueVehicule;
    private String modeleVehicule;
}
