package ma.enset.tonnom.assurance.repositories;

import ma.enset.tonnom.assurance.entities.ContratAssurance;
import ma.enset.tonnom.assurance.entities.StatutContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContratAssuranceRepository extends JpaRepository<ContratAssurance, Long> {
    List<ContratAssurance> findByClientId(Long clientId);
    List<ContratAssurance> findByStatut(StatutContrat statut);
}