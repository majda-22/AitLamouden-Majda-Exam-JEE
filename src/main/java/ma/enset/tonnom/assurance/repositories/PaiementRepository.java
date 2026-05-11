package ma.enset.tonnom.assurance.repositories;

import ma.enset.tonnom.assurance.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByContratId(Long contratId);
}