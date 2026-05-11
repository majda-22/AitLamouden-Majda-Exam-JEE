package ma.enset.tonnom.assurance.repositories;

import ma.enset.tonnom.assurance.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}