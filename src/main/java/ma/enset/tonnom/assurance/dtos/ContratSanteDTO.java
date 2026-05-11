package ma.enset.tonnom.assurance.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ma.enset.tonnom.assurance.entities.NiveauCouverture;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContratSanteDTO extends ContratAssuranceDTO {
    private NiveauCouverture niveauCouverture;
    private Integer nombrePersonnesCouvertes;
}
