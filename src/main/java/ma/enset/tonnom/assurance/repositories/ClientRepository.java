package ma.enset.tonnom.assurance.repositories;

import ma.enset.tonnom.assurance.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
