package ma.enset.tonnom.assurance.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ma.enset.tonnom.assurance.entities.TypeLogement;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContratHabitationDTO extends ContratAssuranceDTO {
    private TypeLogement typeLogement;
    private String adresse;
    private Double superficie;
}
